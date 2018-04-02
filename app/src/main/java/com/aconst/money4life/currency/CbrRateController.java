package com.aconst.money4life.currency;

import android.support.annotation.Nullable;
import android.util.Log;

import com.aconst.money4life.controllers.CurrencyController;
import com.aconst.money4life.controllers.RatesController;
import com.aconst.money4life.model.Currency;
import com.aconst.money4life.model.Rates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CbrRateController implements Callback<CbrValcurs> {
    private static final String TAG = "CbrRateController";
    static final String BASE_URL = "http://www.cbr.ru/scripts/";

    public void start(String date1, String date2, String currencyCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CbrRateAPI cbrRateAPI = retrofit.create(CbrRateAPI.class);

        Call<CbrValcurs> call = cbrRateAPI.getData(date1, date2, currencyCode);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CbrValcurs> call, Response<CbrValcurs> response) {
        if (response.isSuccessful()) {
            CbrValcurs valcurs = response.body();
            if (valcurs != null) {
                CurrencyController currencyController = new CurrencyController();
                Currency currency = currencyController.getCurrencyCbr(valcurs.getId()).blockingGet();

                List<Rates> rates = new ArrayList<>();
                RatesController ratesController = new RatesController();

                for (CbrRecord cbrRecord : valcurs.getRecordList()) {
                    // Загрузка курсов в базу
                    Rates rate = new Rates();
                    rate.setId(cbrRecord.getDate() + currency.getCbrCode());
                    rate.setCurrency(currency);
                    rate.setDate(getDate(cbrRecord.getDate()));
                    rate.setValue(Float.valueOf(cbrRecord.getValue().replace(',', '.')));

                    rates.add(rate);
                }
                ratesController.updateRates(rates);

            } else {
                Log.d(TAG, "response body is null");
            }

        } else {
            Log.d(TAG, response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<CbrValcurs> call, Throwable t) {
        Log.d(TAG, t.toString());
    }

    @Nullable
    private Date getDate(String mDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        try {
            return sdf.parse(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

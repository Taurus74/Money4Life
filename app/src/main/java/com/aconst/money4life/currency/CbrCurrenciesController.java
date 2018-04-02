package com.aconst.money4life.currency;

import android.util.Log;

import com.aconst.money4life.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CbrCurrenciesController implements Callback<CbrValuta> {
    private static final String TAG = "CbrCurrenciesController";
    private static final String BASE_URL = "http://www.cbr.ru/scripts/";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        CbrCurrenciesAPI cbrCurrenciesAPI = retrofit.create(CbrCurrenciesAPI.class);

        Call<CbrValuta> call = cbrCurrenciesAPI.getData();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CbrValuta> call, Response<CbrValuta> response) {
        if (response.isSuccessful()) {
            CbrValuta cbrValuta = response.body();
            for (CbrCurrenciesItem cbrCurrenciesItem : cbrValuta.getCbrCurrenciesItemList()) {
                Currency currency = new Currency();
                currency.setCbrCode(cbrCurrenciesItem.getId());
                currency.setName(cbrCurrenciesItem.getName());
                currency.setEngName(cbrCurrenciesItem.getEngName());
                //ToDo...
            }

        } else {
            Log.d(TAG, response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<CbrValuta> call, Throwable t) {
        Log.d(TAG, t.toString());
    }
}

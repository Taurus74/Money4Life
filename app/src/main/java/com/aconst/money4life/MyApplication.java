package com.aconst.money4life;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aconst.money4life.controllers.CurrencyController;
import com.aconst.money4life.controllers.RatesController;
import com.aconst.money4life.currency.CbrCurrenciesController;
import com.aconst.money4life.currency.CbrRateController;
import com.aconst.money4life.currency.CbrValuta;
import com.aconst.money4life.model.Currency;
import com.aconst.money4life.model.Rates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static final long TIME_SHIFT = 24*60*60*1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        Log.d(TAG, "MyApplication.onCreate");
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("money4life.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        );

        // Загрузка валют по умолчанию
        // Коды интересующих валют сайта ЦБ РФ (http://www.cbr.ru/scripts/XML_val.asp?d=0)
        List<Currency> currencies;
        CurrencyController currencyController = new CurrencyController();

        if (currencyController.getCount().blockingGet() == 0) {
            Realm realm = Realm.getDefaultInstance();

            currencies = loadCurrencies();
            if (currencies != null) {
                realm.beginTransaction();
                realm.insertOrUpdate(currencies);
                realm.commitTransaction();
                realm.close();
            }
        }

        // Загрузка курсов валют
        RatesController ratesController = new RatesController();
        currencyController = new CurrencyController();
        currencies = currencyController.getCurrencies().blockingGet();

        for (Currency currency : currencies) {
            String cbrCode = currency.getCbrCode();
            if (!cbrCode.isEmpty()) {
                // Определить начальную дату по уже загруженным курсам
                Date date = ratesController.getLastDate(cbrCode);
                if (date == null) {
                    date = new Date();
                    date.setTime(date.getTime() - (30 * TIME_SHIFT));
                }
                String date1 = getDateAsString(date);

                // Определить конечную дату по сегодняшней +1 день
                date = new Date();
                date.setTime(date.getTime() + TIME_SHIFT);
                String date2 = getDateAsString(date);

                CbrRateController cbrRateController = new CbrRateController();
                cbrRateController.start(date1, date2, cbrCode);
            }
        }
    }

    @Nullable
    private List<Currency> loadCurrencies() {
        InputStream stream;
        try {
            stream = getAssets().open("currencies.json");
        } catch (IOException e) {
            Log.d(TAG, e.toString());
            return null;
        }

        Gson gson = new GsonBuilder().create();

        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));

        return gson.fromJson(json, new TypeToken<List<Currency>>() {
        }.getType());
    }

    public static String getDateAsString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }

    public static String getDateAsString(int year, int month, int dayOfMonth) {
        return getDateAsString(getDate(year, month, dayOfMonth));
    }

    public static Date getDate(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        Date date = new Date();
        date.setTime(cal.getTimeInMillis());
        return date;
    }
}

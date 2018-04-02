package com.aconst.money4life.controllers;

import com.aconst.money4life.model.Currency;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class CurrencyController {
    private RealmConfiguration realmConfiguration;

    public CurrencyController() {
        realmConfiguration = Realm.getDefaultConfiguration();
    }

    public Single<List<Currency>> getCurrencies() {
        Realm realm = Realm.getInstance(realmConfiguration);
        List<Currency> currencies = realm.copyFromRealm(realm.where(Currency.class).findAll());
        realm.close();
        return Single.just(currencies);
    }

    public Single<Currency> getCurrency(String code) {
        Realm realm = Realm.getInstance(realmConfiguration);
        Currency currency = null;
        Currency realmCurrency = realm.where(Currency.class).equalTo("code", code).findFirst();
        if (realmCurrency != null) {
            currency = realm.copyFromRealm(realmCurrency);
        }
        realm.close();
        return Single.just(currency);
    }

    public Single<Currency> getCurrencyCbr(String CbrCode) {
        Realm realm = Realm.getInstance(realmConfiguration);
        Currency currency = null;
        Currency realmCurrency = realm.where(Currency.class).equalTo("CbrCode", CbrCode).findFirst();
        if (realmCurrency != null) {
            currency = realm.copyFromRealm(realmCurrency);
        }
        realm.close();
        return Single.just(currency);
    }

    public Completable updateCurrencies(final List<Currency> currencies) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm realm = Realm.getInstance(realmConfiguration);
                realm.insertOrUpdate(currencies);
                realm.close();
                Realm.compactRealm(realmConfiguration);
            }
        });
    }

    public Completable updateCurrency(final Currency currency) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm realm = Realm.getInstance(realmConfiguration);
                realm.insertOrUpdate(currency);
                realm.close();
                Realm.compactRealm(realmConfiguration);
            }
        });
    }

    public Single<Long> getCount() {
        Realm realm = Realm.getDefaultInstance();
        return Single.just(realm.where(Currency.class).count());
    }
}

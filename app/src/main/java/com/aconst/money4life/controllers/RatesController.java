package com.aconst.money4life.controllers;

import com.aconst.money4life.model.Rates;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RatesController {
    private RealmConfiguration realmConfiguration;

    public RatesController() {
        realmConfiguration = Realm.getDefaultConfiguration();
    }

    public Single<List<Rates>> getRates() {
        Realm realm = Realm.getInstance(realmConfiguration);
        List<Rates> rates = realm.copyFromRealm(realm.where(Rates.class).findAll());
        realm.close();
        return Single.just(rates);
    }

    public Single<Rates> getRate(String code) {
        Realm realm = Realm.getInstance(realmConfiguration);
        Rates rate = null;
        Rates realmRates = realm.where(Rates.class).equalTo("code", code).findFirst();
        if (realmRates != null) {
            rate = realm.copyFromRealm(realmRates);
        }
        realm.close();
        return Single.just(rate);
    }

    public Date getLastDate(String code) {
        Realm realm = Realm.getInstance(realmConfiguration);
        Date date = null;
        io.realm.RealmResults<Rates> results = realm.where(Rates.class)
            .equalTo("currency.CbrCode", code).findAll();
        if (results != null && !results.isEmpty()) {
            Rates rate = results.sort("date", io.realm.Sort.DESCENDING).first();
            if (rate != null) {
                date = realm.copyFromRealm(rate).getDate();
            }
        }
        realm.close();
        return date;
    }

    public void updateRate(Rates rate) {
        Realm realm = Realm.getInstance(realmConfiguration);
        realm.beginTransaction();
        realm.insertOrUpdate(rate);
        realm.commitTransaction();
        realm.close();
        Realm.compactRealm(realmConfiguration);
    }

    public void updateRates(List<Rates> rates) {
        Realm realm = Realm.getInstance(realmConfiguration);
        realm.beginTransaction();
        realm.insertOrUpdate(rates);
        realm.commitTransaction();
        realm.close();
        Realm.compactRealm(realmConfiguration);
    }

    public void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
        Realm.compactRealm(realmConfiguration);
    }

    public Single<Long> getCount() {
        Realm realm = Realm.getDefaultInstance();
        return Single.just(realm.where(Rates.class).count());
    }

}

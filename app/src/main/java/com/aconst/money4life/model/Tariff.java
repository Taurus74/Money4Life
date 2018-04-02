package com.aconst.money4life.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tariff extends RealmObject {
    public static final String[] tariffType = {"дневной", "ночной"};

    @PrimaryKey
    private int id;
    private Date date;
    private Counter counter;
    private int tariff;
    private float price;
    private boolean synced;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public int getTariff() {
        return tariff;
    }

    public String getTariffStr() {
        return tariffType[tariff];
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }
}

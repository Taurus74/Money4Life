package com.aconst.money4life.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Actions extends RealmObject {
    @PrimaryKey
    private int id;
    private Date date;
    private Accounts account;
    private Operations operation;
    private float value;
    private float valueCur;
    private float commission;
    private String comment;
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

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValueCur() {
        return valueCur;
    }

    public void setValueCur(float valueCur) {
        this.valueCur = valueCur;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }
}

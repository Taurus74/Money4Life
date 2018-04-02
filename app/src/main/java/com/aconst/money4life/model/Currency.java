package com.aconst.money4life.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Currency extends RealmObject {
    @PrimaryKey
    private int id;
    private String code;
    private String CbrCode;
    private String name;
    private String engName;
    private String shortName;
    private boolean synced;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCbrCode() {
        return CbrCode;
    }

    public void setCbrCode(String cbrCode) {
        CbrCode = cbrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

}

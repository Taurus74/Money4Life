package com.aconst.money4life.currency;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Item",strict = false)
public class CbrCurrenciesItem {
    @Element(name = "ID")
    private String id;
    @Element(name = "Name")
    private String name;
    @Element(name = "EngName")
    private String engName;
    @Element(name = "Nominal")
    private int nominal;
    @Element(name = "ParentCode")
    private String parentCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}

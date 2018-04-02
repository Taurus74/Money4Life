package com.aconst.money4life.currency;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "Valuta",strict = false)
public class CbrValuta {
    @Element(name = "name")
    private String name;

    @Element(name = "Item")
    private List<CbrCurrenciesItem> cbrCurrenciesItemList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CbrCurrenciesItem> getCbrCurrenciesItemList() {
        return cbrCurrenciesItemList;
    }

    public void setCbrCurrenciesItemList(List<CbrCurrenciesItem> cbrCurrenciesItemList) {
        this.cbrCurrenciesItemList = cbrCurrenciesItemList;
    }
}

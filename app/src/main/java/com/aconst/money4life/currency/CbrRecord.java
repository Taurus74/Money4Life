package com.aconst.money4life.currency;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "Record", strict = false)
public class CbrRecord {
    @Attribute(name = "Date")
    private String date;

    @Element(name = "Nominal")
    private int nominal;

    @Element(name = "Value")
    private String value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

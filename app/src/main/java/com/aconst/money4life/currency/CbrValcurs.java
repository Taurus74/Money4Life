package com.aconst.money4life.currency;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs", strict = false)
public class CbrValcurs {
    @Attribute(name = "ID")
    private String id;

    @Attribute(name = "DateRange1")
    private String dateRange1;

    @Attribute(name = "DateRange2")
    private String dateRange2;

    @Attribute(name = "name")
    private String name;

    @ElementList(name = "Record", inline = true)
    private List<CbrRecord> recordList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateRange1() {
        return dateRange1;
    }

    public void setDateRange1(String dateRange1) {
        this.dateRange1 = dateRange1;
    }

    public String getDateRange2() {
        return dateRange2;
    }

    public void setDateRange2(String dateRange2) {
        this.dateRange2 = dateRange2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CbrRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<CbrRecord> recordList) {
        this.recordList = recordList;
    }
}

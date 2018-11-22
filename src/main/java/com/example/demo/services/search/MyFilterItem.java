package com.example.demo.services.search;

import com.example.demo.entity.IEnumColumnNames;

import java.util.Date;

public abstract class MyFilterItem {
    Datable datable;
    Searchable searchable;
    Checkable checkable;
    //private EnumColumnNamesForCar enumColumnNamesFor;
    private IEnumColumnNames enumColumnNamesFor;
    private boolean data;
    private boolean text;
    private boolean check;

    public MyFilterItem(IEnumColumnNames enumColumnNamesForCar) {
        this.enumColumnNamesFor = enumColumnNamesForCar;
    }

    public String[] getTexForSearch() {
        return searchable.getTextForSearch();
    }

    public Date[] getDateForSearch() {
        return datable.getDateForSearch();
    }

    public boolean isChecked(){
        return checkable.isCheck();
    }

    public void setDatable(Datable datable) {
        if(datable != null){
            data = true;
        }
        this.datable = datable;
    }

    public void setSearchable(Searchable searchable) {
        if(searchable != null){
            text = true;
        }
        this.searchable = searchable;
    }

    public void setCheckable(Checkable checkable) {
        if(checkable != null){
            check = true;
        }
        this.checkable = checkable;
    }

    public boolean isDate() {
        return data;
    }

    public boolean isText() {
        return text;
    }

    public boolean isCheck() {
        return check;
    }

    public IEnumColumnNames getEnumColumnNamesFor() {
        return enumColumnNamesFor;
    }
}

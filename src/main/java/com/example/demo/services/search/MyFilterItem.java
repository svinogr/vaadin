package com.example.demo.services.search;

import com.example.demo.entity.IEnumColumnNames;

import java.util.Date;

public abstract class MyFilterItem {
    Datable datable;
    Searchable searchable;
    Checkable checkable;
    Parentable parentable;
    private IEnumColumnNames enumColumnNamesFor;
    private boolean data;
    private boolean text;
    private boolean check;
    private boolean parent;

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
    public long getParentIdForSearch(){
        return parentable.getParentIdForSearch();
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

    public void setParentable(Parentable parentable){
        if(parentable != null){
            parent = true;
        }
        this.parentable = parentable;
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

    public boolean isParent() {return parent;}

    public IEnumColumnNames getEnumColumnNamesFor() {
        return enumColumnNamesFor;
    }

    @Override
    public String toString() {
        return "MyFilterItem{" +
                "data=" + data +
                ", text=" + text +
                ", check=" + check +
                ", parent=" + parent +
                '}';
    }
}

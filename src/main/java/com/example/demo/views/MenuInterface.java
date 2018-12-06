package com.example.demo.views;

import com.example.demo.services.search.MyFilterItem;

public interface MenuInterface {
    MyFilterItem getFilterItem();
    void setValidationAction(ValidationAction validationAction);
}

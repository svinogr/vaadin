package com.example.demo.views;

import com.example.demo.entity.Selectable;
import com.example.demo.services.search.MyFilterItem;

public interface GridInterface {
    void searchByFilterItem(MyFilterItem myFilterItem);

    Selectable getSelectedItem();
}

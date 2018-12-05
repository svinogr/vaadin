package com.example.demo.views;

import com.example.demo.services.search.MyFilterItem;

import java.io.FileInputStream;

public interface Downloadedable {
    FileInputStream getStreamItemByFilterItem(MyFilterItem myFilterItem);
}

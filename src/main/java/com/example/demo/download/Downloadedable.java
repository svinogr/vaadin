package com.example.demo.download;

import com.example.demo.services.search.MyFilterItem;

import java.io.FileInputStream;

public interface Downloadedable {
    byte[] getBytesByFilterItem(MyFilterItem myFilterItem);
}

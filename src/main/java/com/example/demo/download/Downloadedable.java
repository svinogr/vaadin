package com.example.demo.download;

import com.example.demo.services.search.MyFilterItem;
import org.apache.poi.ss.usermodel.Workbook;

public interface Downloadedable {
    Workbook getWorkbookForDownload(MyFilterItem myFilterItem);
}

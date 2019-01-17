package com.example.demo.upload;

import org.apache.poi.ss.usermodel.Workbook;

public interface Uploadable {
    boolean saveWorkbook(Workbook workbook);
}

package com.example.demo.upload;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface Uploadable<T> {
    List<T> saveWorkbook(Workbook workbook);
}

package com.example.demo.upload.excell;

import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

@SpringComponent
public class JournalUploadExcelItem extends AbstractUploadExcel<JournalItem> {
    public JournalUploadExcelItem(JournalService itemService) {
        super(itemService);
    }

    @Override
    public List<JournalItem> parseWorkbook(Workbook workbook) {
        return null;
    }
}

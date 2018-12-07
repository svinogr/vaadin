package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.ItemService;
import com.example.demo.services.JournalService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

//@SpringComponent
public class JournalExcelItem extends AbstractExcelItem<JournalItem> {
    public JournalExcelItem(JournalService itemService) {
        super(itemService);
    }


    @Override
    protected void createFirstSheet() {

    }

    @Override
    protected void setTittlesForSheet() {

    }

    @Override
    protected void inflateWorkbook(List<JournalItem> list) {

    }
}

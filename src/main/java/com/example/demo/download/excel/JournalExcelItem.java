package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.ItemService;
import com.example.demo.services.JournalService;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class JournalExcelItem extends AbstractExcelItem<JournalItem> {
    public JournalExcelItem(JournalService itemService) {
        super(itemService);
    }
}

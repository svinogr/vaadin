package com.example.demo.views.journalview;

import com.example.demo.download.excel.JournalExcelItem;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.ParentValue;
import com.example.demo.upload.excell.JournalUploadExcelItem;
import com.example.demo.views.AbstractMiddleByParentView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class JournalView extends AbstractMiddleByParentView {
    public final static String ID_VIEW = "JOURNAL_VIEW";

    public JournalView(JournalMenuView menuInterface, JournalGridView gridInterface, JournalExcelItem journalExcelItem, JournalUploadExcelItem journalUploadExcelItem) {
        super(menuInterface, gridInterface, journalExcelItem, journalUploadExcelItem);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    protected MyFilterItem getDefaultMyFilterItem() {
        return new ParentValue(null);
    }
}

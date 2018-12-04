package com.example.demo.views.journalview;

import com.example.demo.entity.jornal.EnumColumnNameForJournal;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.ParentValue;
import com.example.demo.views.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class JournalViewNew extends AbstractMiddleByParentView {
    public final static String ID_VIEW = "JOURNAL_VIEW";
    public JournalViewNew(JournalMenuView menuInterface, JournalGridView gridInterface) {
        super(menuInterface, gridInterface);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    protected MyFilterItem getDefaiultMyFilterItem() {
        return new ParentValue(null);
    }
}

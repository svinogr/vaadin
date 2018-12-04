package com.example.demo.views.journalview;

import com.example.demo.views.AbstractMiddleView;
import com.example.demo.views.GridInterface;
import com.example.demo.views.MenuInterface;
import com.example.demo.views.SelectByParent;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class JournalViewNew extends AbstractMiddleView implements SelectByParent {
    public final static String ID_VIEW = "JOURNAL_VIEW";
    public JournalViewNew(JournalMenuView menuInterface, JournalGridView gridInterface) {
        super(menuInterface, gridInterface);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    public void updateByParent(long id) {

    }
}

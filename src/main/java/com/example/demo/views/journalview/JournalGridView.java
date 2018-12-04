package com.example.demo.views.journalview;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.JournalEditor;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.ItemService;
import com.example.demo.services.JournalService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class JournalGridView extends AbstractGridView<JournalItem> {
    public JournalGridView(JournalService itemService, JournalEditor editor) {
        super(itemService, editor, JournalItem.class);
    }

    @Override
    protected void createGrid() {

    }
}

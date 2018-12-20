package com.example.demo.views.journalview;

import com.example.demo.editors.JournalEditor;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class JournalGridView extends AbstractGridView<JournalItem> {
    public JournalGridView(JournalService itemService, JournalEditor editor) {
        super(itemService, editor, JournalItem.class);
    }

    @Override
    protected JournalItem createNewInstanceItem() {
        JournalItem journalItem = new JournalItem();
        journalItem.setCar_id(myFilterItem.getParentIdForSearch());
        return journalItem;
    }

    @Override
    protected void createGrid() {
        grid = new Grid<>();
        grid.addColumn(journalItem -> journalItem.getEnumTypeRecord()).setHeader("Тип записи").setResizable(true);
        grid.addColumn(journalItem -> journalItem.isClosed() ? "Закрыто" : "").setHeader("Статус").setResizable(true);
        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<JournalItem>, JournalItem>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<JournalItem>, JournalItem> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedItem = event.getFirstSelectedItem().get();
                }
            }
        });

        add(grid);
    }
}

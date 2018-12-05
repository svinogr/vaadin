package com.example.demo.views.organisationview;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.OrganisationEditorG;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.ItemService;
import com.example.demo.services.OrganisationService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class OrganisationGridView extends AbstractGridView<Organisation> {
    public OrganisationGridView(OrganisationService itemService, OrganisationEditorG editor) {
        super(itemService, editor, Organisation.class);
    }

    @Override
    protected Organisation createNewInstanceItem() {
        return new Organisation();
    }

    @Override
    protected void createGrid() {
        grid = new Grid<>();
        grid.addColumn(organisation -> organisation.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(organisation -> organisation.getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(organisation -> organisation.getPhone()).setHeader("Телефон").setResizable(true);
        grid.addColumn(organisation -> organisation.getInn()).setHeader("ИНН").setResizable(true);
        grid.addColumn(organisation -> organisation.getOgrn()).setHeader("ОГРН").setResizable(true);

        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Organisation>, Organisation>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Organisation>, Organisation> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedItem = event.getFirstSelectedItem().get();
                }
            }
        });

        add(grid);
    }
}

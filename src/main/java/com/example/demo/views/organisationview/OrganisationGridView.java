package com.example.demo.views.organisationview;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.OrganisationEditorG;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.ItemService;
import com.example.demo.services.OrganisationService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class OrganisationGridView extends AbstractGridView<Organisation> {
    public OrganisationGridView(OrganisationService itemService, OrganisationEditorG editor) {
        super(itemService, editor, Organisation.class);
    }

    @Override
    protected void createGrid() {

    }
}

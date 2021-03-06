package com.example.demo.views.organisationview;

import com.example.demo.download.excel.OrganisationExcelItem;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class OrganisationView extends AbstractMiddleView {
    public final static String ID_VIEW = "ORGANISATION_VIEW";

    public OrganisationView(OrganisationMenuView menuInterface, OrganisationGridView gridInterface, OrganisationExcelItem organisationExcelItem) {
        super(menuInterface, gridInterface, organisationExcelItem, null);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }
}

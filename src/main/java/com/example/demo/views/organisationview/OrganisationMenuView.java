package com.example.demo.views.organisationview;

import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.views.AbstractMenuView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class OrganisationMenuView extends AbstractMenuView<EnumColumnNameForOrg> {
    @Override
    protected void createSearchMenu() {

    }

    @Override
    protected void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg> event) {

    }

    @Override
    public MyFilterItem getFilterItem() {
        return null;
    }
}

package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.ItemService;
import com.example.demo.services.OrganisationService;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class OrganisationExcelItem extends AbstractExcelItem<Organisation> {
    public OrganisationExcelItem(OrganisationService itemService) {
        super(itemService);
    }
}

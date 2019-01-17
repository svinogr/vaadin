package com.example.demo.upload.excell;

import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

@SpringComponent
public class OrganisationUploadExcelItem extends AbstractUploadExcel<Organisation> {
    public OrganisationUploadExcelItem(OrganisationService itemService) {
        super(itemService);
    }

    @Override
    public List<Organisation> parseWorkbook(Workbook workbook) {
        return null;
    }
}

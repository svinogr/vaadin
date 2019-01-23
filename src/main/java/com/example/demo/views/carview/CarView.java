package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
import com.example.demo.upload.editor.CarUploadEditor;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.poi.ss.usermodel.Workbook;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";
    private ProgressBar bar;
    private Workbook workbook;

    public CarView(CarMenuView carMenu, CarGridView carGridView, CarExcelItem carExcelItem, CarUploadEditor carUploadEditor) {
        super(carMenu, carGridView, carExcelItem, carUploadEditor);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    protected void addAdditionalButtons() {

        Button uploadButton = new Button("Импорт из Excell");
        uploadButton.addClickListener((e) -> {
            showUploadDialog();

        });
        btnLayout.add(uploadButton);
    }

    private void showUploadDialog() {
        Dialog dialog = new Dialog();

        dialog.add(abstractUndoableEdit);
        dialog.setHeight("270px");
        dialog.setWidth("450px");
        dialog.open();

    }

}

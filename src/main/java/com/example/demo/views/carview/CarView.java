package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
import com.example.demo.upload.excell.CarUploadExcelItem;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";

    public CarView(CarMenuView carMenu, CarGridView carGridView, CarExcelItem carExcelItem, CarUploadExcelItem carUploadExcelItem) {
        super(carMenu, carGridView, carExcelItem, carUploadExcelItem);

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

        VerticalLayout layout = new VerticalLayout();

        ProgressBar progressBar = new ProgressBar();
        progressBar.setVisible(false);

        HorizontalLayout layoutBtn = new HorizontalLayout();

        Button startBtn = new Button("Записать в базу", VaadinIcon.DATABASE.create());
        startBtn.setEnabled(false);
        Button cancelBtn = new Button("Отмена");
        cancelBtn.setSizeFull();
        startBtn.setSizeFull();

        layoutBtn.add(cancelBtn, startBtn);
        layoutBtn.setSizeFull();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

        final String[] name = new String[1];

        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/vnd.ms-excel");
        upload.setMaxFiles(1);
        upload.setSizeFull();
        upload.addSucceededListener((event) -> {
            name[0] = event.getFileName();
            startBtn.setEnabled(true);

        });

        startBtn.addClickListener((event) -> {
            if (name[0] != null) {

                progressBar.setVisible(true);
                progressBar.setIndeterminate(true);

                try (InputStream inputStream = buffer.getInputStream(name[0])) {

                    Workbook workbook = WorkbookFactory.create(inputStream);
                    parseAndSaveWorkbook(workbook);

                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);

                } catch (IOException e) {
                    e.printStackTrace();
                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);
                }


            }
        });

        layout.add(progressBar, upload, layoutBtn);
        layout.setAlignItems(Alignment.CENTER);

        dialog.add(layout);
        dialog.setHeight("270px");
        dialog.setWidth("450px");
        dialog.open();

    }

    private void parseAndSaveWorkbook(Workbook workbook) {
        uploadable.saveWorkbook(workbook);

    }
}

package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
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
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";

    public CarView(CarMenuView carMenu, CarGridView carGridView, CarExcelItem carExcelItem) {
        super(carMenu, carGridView, carExcelItem);

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

//
//        //upload.setUploadButton(uploadButton);
//        upload.setMaxFiles(1);
//
//        btnLayout.add(upload);
//
//        upload.addSucceededListener(event ->{
//            System.out.println(event.getFileName());
//            InputStream inputStream = buffer.getInputStream(event.getFileName());
//            buffer.
//            File file = inputStream.
//
//        });

//        toExcelBtn = new Anchor(getStream(), "");
//        toExcelBtn.getElement().setAttribute("download", true);
//        Button forAnchor = new Button("Импорт Excell");
//        ExcelBtn.add(forAnchor);

    }

    private void showUploadDialog() {
        Dialog dialog = new Dialog();

        VerticalLayout layout = new VerticalLayout();

        ProgressBar progressBar = new ProgressBar();
        progressBar.setVisible(false);

        HorizontalLayout layoutBtn = new HorizontalLayout();

        Button startBtn = new Button("Записать в базу", VaadinIcon.DATABASE.create());
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

            //   ByteArrayOutputStream outputBuffer = buffer.getOutputBuffer(event.getFileName());
            try (InputStream inputStream = buffer.getInputStream(name[0]);
                 ByteArrayOutputStream outputBuffer = buffer.getOutputBuffer(event.getFileName());
            ) {

                System.out.println(inputStream == null);
                //POIFSFileSystem f  =  new POIFSFileSystem(inputStream);
                Workbook workbook = WorkbookFactory.create(inputStream);
                // XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook(inputStream);
                //  HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
                parseAndSaveWorkbook(workbook);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startBtn.addClickListener((event) -> {
            if (name[0] != null) {

//                progressBar.setVisible(true);
//                progressBar.setIndeterminate(true);
//
//                try (InputStream inputStream = buffer.getInputStream(name[0])) {
//
//                    System.out.println(inputStream == null);
//                    //POIFSFileSystem f  =  new POIFSFileSystem(inputStream);
//
//                    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
//                    parseAndSaveWorkbook(hssfWorkbook);
//
//                    progressBar.setIndeterminate(false);
//                    progressBar.setVisible(false);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }
        });

        layout.add(progressBar, upload, layoutBtn);
        layout.setAlignItems(Alignment.CENTER);

        dialog.add(layout);
        dialog.setHeight("270px");
        dialog.setWidth("450px");
        dialog.open();

    }

    private void parseAndSaveWorkbook(Workbook hssfWorkbook) {
        Sheet sheet = hssfWorkbook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                String stringCellValue = cell.getStringCellValue();
                System.out.println(stringCellValue);
            }
        }
    }
}

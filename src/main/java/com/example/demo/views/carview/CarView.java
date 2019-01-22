package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
import com.example.demo.entity.cars.car.Car;
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
import java.util.List;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";
    private ProgressBar bar;
    private Workbook workbook;

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

        VerticalLayout progresLayout = new VerticalLayout();
        bar = new ProgressBar();
        progresLayout.add(bar);
        bar.setVisible(false);

        HorizontalLayout layoutBtn = new HorizontalLayout();

        Button startBtn = new Button("Записать в базу", VaadinIcon.DATABASE.create());
        startBtn.setEnabled(false);

        Button cancelBtn = new Button("Отмена");
        cancelBtn.setSizeFull();
        startBtn.setSizeFull();

        layoutBtn.add(cancelBtn, startBtn);
        layoutBtn.setSizeFull();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/vnd.ms-excel");
        upload.setMaxFiles(1);
        upload.setSizeFull();
        upload.addSucceededListener((event) -> {
            String name = event.getFileName();
            try {
                workbook = WorkbookFactory.create(buffer.getInputStream(name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            startBtn.setEnabled(true);

        });

        startBtn.addClickListener((event) -> {
            if (workbook == null) return;
            System.out.println("1");

            bar.setVisible(true);
            System.out.println("2");

            bar.setIndeterminate(true);
            System.out.println("3");

            startBtn.setEnabled(false);

            parseAndSaveWorkbook(workbook);


            System.out.println("dwdwd");
            //}
            workbook = null;
            //bar.setIndeterminate(false);
        });

        layout.add(progresLayout, upload, layoutBtn);
        layout.setAlignItems(Alignment.CENTER);

        dialog.add(layout);
        dialog.setHeight("270px");
        dialog.setWidth("450px");
        dialog.open();

    }

    private void parseAndSaveWorkbook(Workbook workbook) {
        System.out.println("4");

        List<Car> list = uploadable.saveWorkbook(workbook);

    }
}

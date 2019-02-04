package com.example.demo.upload.editor;

import com.example.demo.editors.ChangeHandler;
import com.example.demo.upload.Uploadable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.dom.DomEventListener;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;


public class AbstractUploadEditor<T> extends VerticalLayout {
    protected final String TYPE_UPLOAD_FILE = "application/vnd.ms-excel";
    protected Uploadable<T> uploadable;
    protected Workbook workbook;
    private FeederThread feederThread;
    private ProgressBar bar;
    private Button startBtn;
    private Button cancelBtn;
    private Upload upload;
    private ChangeHandler changeHandler;

    public void setChangeHandler(ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }

    public AbstractUploadEditor(Uploadable<T> uploadable) {
        this.uploadable = uploadable;
        setup();
    }

    private void progressBarChange(boolean value) {
        bar.setVisible(value);
        bar.setIndeterminate(value);
        startBtn.setEnabled(false);
        cancelBtn.setEnabled(!value);
        upload.getElement().setEnabled(value);
    }

    private void setup() {
        VerticalLayout progresLayout = new VerticalLayout();
        bar = new ProgressBar();
        progresLayout.add(bar);
        bar.setVisible(false);

        HorizontalLayout layoutBtn = new HorizontalLayout();

        startBtn = new Button("Записать в базу", VaadinIcon.DATABASE.create());
        startBtn.setEnabled(false);

        cancelBtn = new Button("Отмена");
        cancelBtn.setSizeFull();
        startBtn.setSizeFull();

        layoutBtn.add(cancelBtn, startBtn);
        layoutBtn.setSizeFull();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

        upload = new Upload(buffer);
        upload.setAcceptedFileTypes(TYPE_UPLOAD_FILE);
        upload.setMaxFiles(1);
        upload.setSizeFull();
        upload.addSucceededListener((event) -> {
            String name = event.getFileName();
            boolean flag = false;
            try {
                workbook = WorkbookFactory.create(buffer.getInputStream(name));
            } catch (IOException e) {
                e.printStackTrace();
                flag = true;
            }

            if (flag) {

                cancelBtn.setEnabled(true);
                startBtn.setEnabled(false);
                Notification notification = new Notification(
                        "Неправильный формат файла", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            } else {

                startBtn.setEnabled(true);
            }

        });

        upload.addFailedListener((event -> {
            startBtn.setEnabled(false);

        }));

        upload.getElement().addEventListener("file-remove", new DomEventListener() {
            @Override
            public void handleEvent(DomEvent event) {
                startBtn.setEnabled(false);
                cancelBtn.setEnabled(true);

            }
        });

        startBtn.addClickListener((event) -> {
            feederThread = new FeederThread(getUI().get(), this);
            feederThread.start();
        });

        cancelBtn.addClickListener((event) -> {
            changeHandler.onChange();
        });

        add(progresLayout, upload, layoutBtn);
        setAlignItems(FlexComponent.Alignment.CENTER);
    }


    private void doWork() {
        uploadable.saveWorkbook(workbook);

    }

    private class FeederThread extends Thread {
        private final UI ui;
        private final AbstractUploadEditor view;

        public FeederThread(UI ui, AbstractUploadEditor view) {
            this.ui = ui;
            this.view = view;
        }

        @Override
        public void run() {
            ui.access(() -> {
                view.progressBarChange(true);
            });

            doWork();

            ui.access(() -> {
                view.progressBarChange(false);
            });

        }
    }
}

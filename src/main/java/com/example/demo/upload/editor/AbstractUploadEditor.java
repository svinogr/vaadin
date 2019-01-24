package com.example.demo.upload.editor;

import com.example.demo.upload.Uploadable;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.server.VaadinSession;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.util.List;


public class AbstractUploadEditor<T> extends VerticalLayout {
    protected final String TYPE_UPLOAD_FILE = "application/vnd.ms-excel";
    protected Uploadable<T> uploadable;
    protected Workbook workbook;
    private FeederThread feederThread;
    private ProgressBar bar;
    private Button startBtn;

    public AbstractUploadEditor(Uploadable<T> uploadable) {
        this.uploadable = uploadable;
        setup();
    }

    private void progresBarChanche(boolean value) {
        System.out.println("-0-");
        bar.setVisible(value);
        bar.setIndeterminate(value);
        //  startBtn.setEnabled(value);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        // Start the data feed thread
        //   feederThread = new FeederThread(attachEvent.getUI(), this);
        //feederThread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        if (feederThread != null) {
            feederThread.interrupt();
            feederThread = null;
        }
    }

    private void setup() {
        // feederThread = new FeederThread(this.getUI().get(), this);
        VerticalLayout progresLayout = new VerticalLayout();
        bar = new ProgressBar();
        progresLayout.add(bar);
        bar.setVisible(false);

        HorizontalLayout layoutBtn = new HorizontalLayout();

        startBtn = new Button("Записать в базу", VaadinIcon.DATABASE.create());
        // startBtn.setEnabled(false);

        Button cancelBtn = new Button("Отмена");
        cancelBtn.setSizeFull();
        startBtn.setSizeFull();

        layoutBtn.add(cancelBtn, startBtn);
        layoutBtn.setSizeFull();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes(TYPE_UPLOAD_FILE);
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

//            ForkJoinPool.commonPool().submit(() -> {
//                        int count = 0;
//                        UI.getCurrent().access(() -> {
//                            progresBarChanche(true);
//                        });
//                        while (count < 10) {
//                            // Sleep to emulate background work
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println(count++);
//                            // ui.access(() -> view.add(new Span(message)));
//                        }
//
//                        //  ui.access(() -> view.add(new Label("wdwdwdwdwd")));
//                        // view.parseAndSaveWorkbook(view.workbook);
//
//                        // Inform that we are done
//                        UI.getCurrent().access(() -> {
//                            progresBarChanche(false);
//                        });
//                    }
//            );


            feederThread = new FeederThread(getUI().get(), this);
            System.out.println(UI.getCurrent());
            System.out.println(getUI().get());
            feederThread.start();
            // if (workbook == null) return;
            System.out.println("1");

            //bar.setVisible(true);
            System.out.println("2");

            //bar.setIndeterminate(true);
            System.out.println("3");

            ///startBtn.setEnabled(false);

            //parseAndSaveWorkbook(workbook);


            System.out.println("dwdwd");
            //}
            //workbook = null;
            //bar.setIndeterminate(false);
            //bar.setVisible(false);
        });

        add(progresLayout, upload, layoutBtn);
        setAlignItems(FlexComponent.Alignment.CENTER);
        //  feederThread.start();
    }

    private void parseAndSaveWorkbook(Workbook workbook) {

        System.out.println("4");
//        Thread thread = new Thread(() -> {
//            List<T> list = uploadable.saveWorkbook(workbook);
//
//        });
//        thread.start();
        List<T> list = uploadable.saveWorkbook(workbook);


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
            System.out.println(ui);
            // Update the data for a while
            int count = 0;
            try {
                // view.progresBarChanche(true);
                VaadinSession session = ui.getSession();

                ui.access(() -> {
                    view.progresBarChanche(true);
                });
                // ui.access(() -> view.add(new Span("ssssssssssssss")));
                while (count < 10) {
                    // Sleep to emulate background work
                    Thread.sleep(500);
                    System.out.println(count++);
                    // ui.access(() -> view.add(new Span(message)));
                }

                //  ui.access(() -> view.add(new Label("wdwdwdwdwd")));
                // view.parseAndSaveWorkbook(view.workbook);

                // Inform that we are done
                ui.access(() -> {
                    view.progresBarChanche(false);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

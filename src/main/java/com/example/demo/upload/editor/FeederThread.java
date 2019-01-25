package com.example.demo.upload.editor;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;

public class FeederThread extends Thread {
    private final UI ui;

    public FeederThread(UI ui) {
        this.ui = ui;
    }

    @Override
    public void run() {
        System.out.println(ui);
        // Update the data for a while
        int count = 0;
        try {

            // view.progresBarChanche(true)

            ui.access(() -> {
                Notification notification = new Notification(
                        "ghfgfghfghfhgfhf", 3000,
                        Notification.Position.TOP_START);
                notification.open();
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
                Notification notification = new Notification(
                        "ghfgfghfghfhgfhf", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
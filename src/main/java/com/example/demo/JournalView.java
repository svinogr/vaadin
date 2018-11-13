package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.jornal.JournalItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import static com.sun.tools.internal.xjc.reader.Ring.add;
@SpringComponent
@UIScope
@HtmlImport("styles/styles.html")
public class JournalView  extends VerticalLayout implements HasUrlParameter<Long>, IdViewable {
    private static final String BACK_ROUT = "login";
    public static  final   String ID_VIEW = "JOURNAL_VIEW";

    private Car car;
    private JournalItem selectedJournalItem;
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String OPEN_BTN_TEXT = "Открыть";
    private static final String BACK_BTN_TEXT = "Назад";
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";


    public JournalView() {
      //  this.car = car;
        createActionMenu();

    }
    private void createActionMenu(){
        HorizontalLayout menuLayout = new HorizontalLayout();

        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener(event -> {
            openEditor(new JournalItem());
        });

        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.EDIT.create());
        openBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                if(selectedJournalItem != null){
                    openEditor(selectedJournalItem);
                }
            }
        });

        Button journalBtn = new Button(BACK_BTN_TEXT, VaadinIcon.OPEN_BOOK.create());
        journalBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                 getUI().ifPresent(ui -> ui.navigate(BACK_ROUT));
            }
        });

        menuLayout.add(addBtn, openBtn, journalBtn );
        add(menuLayout);
    }

    private void openEditor(JournalItem journalItem) {

    }


    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        System.out.printf(String.valueOf(parameter));
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }
}

package com.example.demo.views;

import com.example.demo.IdViewable;
import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.LoginService;
import com.example.demo.views.carview.CarViewNew;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@HtmlImport("styles/styles.html")
//@Route(value = "main")
@Route(value = "login")
public class MainView extends VerticalLayout {
//TODO вынести интерфес из кар
    private static final String ORGANISATION_BTN_TEXT = "Организации";
    private static final String PEOPLE_BTN_TEXT = "Персонал";
    private static final String CAR_BTN_TEXT = "Техника";
    private static final String JOURNAL_BTN_TEXT = "Журнал техники";
    private LoginService loginService;

    private static final String NAME_OF_MENU_GENERAL = "Основные";
    private static final String MENU_ITEM_LOGOUT = "Выход";
    private static final String ADD_BTN_TEXT = "Добавить";


    private Selectable selectdItem = null;
    private Map<String, Component> mapView = new HashMap<>();
    private CarViewNew carView;
    private JournalView journalView;
    private PersonalView personalView;
    private OrganisationView organisationView;
    private Label titleLabelForPage;

    public MainView(@Autowired LoginService loginService, @Autowired CarViewNew carView,  @Autowired OrganisationView organisationView,@Autowired JournalView journalView, @Autowired PersonalView personalView) {
        this.carView = carView;
        this.journalView = journalView;
        this.personalView = personalView;
        this.organisationView = organisationView;
        this.loginService = loginService;

        createMenu();
        createTitleForPage();
        createActionMenu();
        addMiddleView(carView);
        changeTitleFroPAge(CAR_BTN_TEXT);
        //setSizeFull(); с этой штукой обрезаются кнопки поиска по таблице!!
    }

    private void createTitleForPage() {
        titleLabelForPage = new Label();
        add(titleLabelForPage);
    }

    private void addMiddleView(Component component) {
        String id = ((IdViewable) component).getIdView();
        mapView.put(id, component);
        add(component);
    }

    private void changeTitleFroPAge(String title) {
        titleLabelForPage.setText(title);
    }

    private void createActionMenu() {
        HorizontalLayout menuLayout = new HorizontalLayout();

        Button carBtn = new Button(CAR_BTN_TEXT, VaadinIcon.CAR.create());
        carBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(CarViewNew.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    mapView.clear();
                    addMiddleView(carView);
                    changeTitleFroPAge(CAR_BTN_TEXT);
                }
                changeTitleFroPAge(CAR_BTN_TEXT);
            }
        });

        Button journalBtn = new Button(JOURNAL_BTN_TEXT, VaadinIcon.OPEN_BOOK.create());
        journalBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(CarViewNew.ID_VIEW);
                Selectable selectable = null;
                if(component != null){
                    selectable = ((CarViewNew)component).getSelectItem();
                }

                if (selectable != null) {
                    if (selectable instanceof Car) {
                        component = mapView.get(JournalView.ID_VIEW);
                        if (component == null) {
                            for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                                remove(stringComponentMap.getValue());
                            }
                            Car car = (Car) selectable;
                            mapView.clear();
                            addMiddleView(journalView);
                            journalView.updateListItems(car.getId());
                        }
                        changeTitleFroPAge(JOURNAL_BTN_TEXT);
                    }
                }
            }
        });

        Button organisationBtn = new Button(ORGANISATION_BTN_TEXT, VaadinIcon.BUILDING.create());
        organisationBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(OrganisationView.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    mapView.clear();
                    addMiddleView(organisationView);
                    changeTitleFroPAge(ORGANISATION_BTN_TEXT);
                }
            }
        });

        Button peopleBtn = new Button(PEOPLE_BTN_TEXT, VaadinIcon.USER.create());
        peopleBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(PersonalView.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    mapView.clear();
                    addMiddleView(personalView);
                    changeTitleFroPAge(PEOPLE_BTN_TEXT);
                }
            }

        });

        menuLayout.add(carBtn, journalBtn, organisationBtn, peopleBtn);

        add(menuLayout);
    }

    private void createMenu() {
        FlexLayout loginFlexLayout = new FlexLayout();
        loginFlexLayout.setWidth("100%");
        loginFlexLayout.setAlignItems(Alignment.BASELINE);

        Label loginNameLabel = new Label(" МарьИванна");

        Button buttonExit = new Button(VaadinIcon.EXIT.create());

        loginFlexLayout.add(loginNameLabel, buttonExit);
        setHorizontalComponentAlignment(Alignment.END, loginFlexLayout);

        add(loginFlexLayout);

    }

}
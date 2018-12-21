package com.example.demo.views;

import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import com.example.demo.views.carview.CarView;
import com.example.demo.views.journalview.JournalView;
import com.example.demo.views.organisationview.OrganisationView;
import com.example.demo.views.personalview.PersonalView;
import com.example.demo.views.userview.UserView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

@HtmlImport("styles/styles.html")
@Route(value = "main")
//@Route(value = "login")
public class MainView extends VerticalLayout {
    //TODO вынести интерфес из кар
    private static final String ORGANISATION_BTN_TEXT = "Организации";
    private static final String PEOPLE_BTN_TEXT = "Персонал";
    private static final String CAR_BTN_TEXT = "Техника";
    private static final String JOURNAL_BTN_TEXT = "Журнал техники";
    private static final String ADMIN_BTN_TEXT = "Управление";
    public static final String EXIT_BTN_TEXT = "Выйти";
    private LoginService loginService;
    private UserService userService;
    private User loged;

    private static final String NAME_OF_MENU_GENERAL = "Основные";
    private static final String MENU_ITEM_LOGOUT = "Выход";
    private static final String ADD_BTN_TEXT = "Добавить";


    private Selectable selectdItem;
    private Map<String, Component> mapView = new HashMap<>();
    private Map<String, Button> mapBtn = new HashMap<>();
    private CarView carView;
    private JournalView journalView;
    private PersonalView personalView;
    private OrganisationView organisationView;
    private UserView userView;

    public MainView(@Autowired LoginService loginService, @Autowired UserService userService, @Autowired CarView carView, @Autowired OrganisationView organisationView, @Autowired JournalView journalView, @Autowired PersonalView personalView, @Autowired UserView userView) {
        this.carView = carView;
        this.journalView = journalView;
        this.personalView = personalView;
        this.organisationView = organisationView;
        this.userView = userView;
        this.loginService = loginService;
        this.userService = userService;

        createUserMenu();
        createActionMenu();
        addMiddleView(carView);
        //setSizeFull(); с этой штукой обрезаются кнопки поиска по таблице!!
    }

    private void addMiddleView(Component component) {
        String id = ((IdViewable) component).getIdView();
        mapView.put(id, component);
        add(component);
    }

    private void createActionMenu() {
        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setPadding(true);
        Button carBtn = new Button(CAR_BTN_TEXT, VaadinIcon.CAR.create());
        mapBtn.put(CAR_BTN_TEXT, carBtn);
        changeColorBtn(CAR_BTN_TEXT);
        carBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(CarView.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    mapView.clear();
                    addMiddleView(carView);
                    changeColorBtn(CAR_BTN_TEXT);
                }
            }
        });

        Button journalBtn = new Button(JOURNAL_BTN_TEXT, VaadinIcon.OPEN_BOOK.create());
        mapBtn.put(JOURNAL_BTN_TEXT, journalBtn);
        journalBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(CarView.ID_VIEW);
                Selectable selectable = null;
                if (component != null) {
                    selectable = ((CarView) component).getSelectItem();
                }

                if (selectable != null) {
                    if (selectable instanceof Car) {
                        changeColorBtn(JOURNAL_BTN_TEXT);
                        component = mapView.get(JournalView.ID_VIEW);
                        if (component == null) {
                            for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                                remove(stringComponentMap.getValue());
                            }
                            Car car = (Car) selectable;
                            mapView.clear();
                            addMiddleView(journalView);
                            journalView.updateByParent(car.getId());
                        }
                    }
                }
            }
        });

        Button organisationBtn = new Button(ORGANISATION_BTN_TEXT, VaadinIcon.BUILDING.create());
        mapBtn.put(ORGANISATION_BTN_TEXT, organisationBtn);
        organisationBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(OrganisationView.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    changeColorBtn(ORGANISATION_BTN_TEXT);
                    mapView.clear();
                    addMiddleView(organisationView);
                }
            }
        });

        Button peopleBtn = new Button(PEOPLE_BTN_TEXT, VaadinIcon.USER.create());
        mapBtn.put(PEOPLE_BTN_TEXT, peopleBtn);
        peopleBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = mapView.get(PersonalView.ID_VIEW);
                if (component == null) {
                    for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                        remove(stringComponentMap.getValue());
                    }
                    changeColorBtn(PEOPLE_BTN_TEXT);
                    mapView.clear();
                    addMiddleView(personalView);
                }
            }

        });

        Button toAdminBtn = new Button(ADMIN_BTN_TEXT, VaadinIcon.COGS.create());
        mapBtn.put(ADMIN_BTN_TEXT, toAdminBtn);
        toAdminBtn.addClickListener((e) -> {
            Component component = mapView.get(UserView.ID_VIEW);
            if (component == null) {
                for (Map.Entry<String, Component> stringComponentMap : mapView.entrySet()) {
                    remove(stringComponentMap.getValue());
                }
                changeColorBtn(ADMIN_BTN_TEXT);
                mapView.clear();
                addMiddleView(userView);
            }

        });

        menuLayout.add(carBtn, journalBtn, organisationBtn, peopleBtn, toAdminBtn);
        if (loged.getRole() != EnumRole.ROLE_ADMIN) {
            toAdminBtn.setVisible(false);
        }

        add(menuLayout);
    }

    private void changeColorBtn(String textBtn) {
        for (Map.Entry<String, Button> stringComponentMap : mapBtn.entrySet()) {
            if (!stringComponentMap.getKey().equals(textBtn)) {
                stringComponentMap.getValue().getElement().getThemeList().remove("primary");
            } else {
                stringComponentMap.getValue().getElement().getThemeList().add("primary");
            }

        }
    }

    private void createUserMenu() {
        VerticalLayout loginFlexLayout = new VerticalLayout();
        loginFlexLayout.setPadding(true);
        loginFlexLayout.setWidth("auto");
        loginFlexLayout.setAlignItems(Alignment.END);

        UserDetails auth = loginService.getAuth();
        String login = auth.getUsername();
        loged = userService.getUserByLogin(login);

        Label loginNameLabel = new Label();
        if(loged != null){
             loginNameLabel.setText(createStringForLoginLabel());
        }


        Button buttonExit = new Button(EXIT_BTN_TEXT, VaadinIcon.EXIT.create());
        buttonExit.addClickListener((e) -> {
            loginService.logout();
            UI.getCurrent().getCurrent().getPage().reload();
        });

        loginFlexLayout.add(loginNameLabel, buttonExit);
        setHorizontalComponentAlignment(Alignment.END, loginFlexLayout);
        add(loginFlexLayout);
    }

    private String createStringForLoginLabel(){
        String surname = loged.getUserInfo().getSurname();
        if (surname.isEmpty()) surname = "Аноним";
        String name = loged.getUserInfo().getName();
        if (name.isEmpty()) surname = "Аноним";
        String all = surname.substring(0,1).toUpperCase() + surname.substring(1)
                +" "+ name.substring(0,1).toUpperCase()+".";
        return all;
    }

}

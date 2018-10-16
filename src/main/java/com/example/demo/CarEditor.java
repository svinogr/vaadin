package com.example.demo;

import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertFalse;
import java.util.HashMap;
import java.util.Map;

@SpringComponent
@UIScope
public class CarEditor extends VerticalLayout {
    private Car car;
    private CarService carService;
    private Button save = new Button("Cохранить");
    private Button cansel = new Button("Отмена");
    private Map<Tab, Component> mapTabs = new HashMap<>();
    private Tabs tabs;


    // @Autowired
    public CarEditor(CarService carService) {
        this.carService = carService;
        createTab();
        createSubmitBtn();



    }

    private void createSubmitBtn() {
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cansel);
        submitLayout.setAlignSelf(Alignment.END, this);
        add(submitLayout);
    }

    private void createTab() {
        Tab general = createGeneralTab();
        Tab pasport = createPassportTab();
        tabs = new Tabs();
        tabs.add(general, pasport);

        tabs.addSelectedChangeListener(event -> {
            System.out.println("hjhfkehfkjef");
            Component pageToShown = mapTabs.get(tabs.getSelectedTab());
            for (Component page: mapTabs.values()){

                if(page == pageToShown ) {
                    page.setVisible(true);
                }else page.setVisible(false);

            }

        });

        add(tabs);
        Component pageToShown = mapTabs.get(tabs.getSelectedTab());
        for (Component page: mapTabs.values()){
            add(page);
        }

    }

    private Tab createPassportTab() {
        Tab pasport = new Tab("Паспортные данные");
        VerticalLayout oneLayout = new VerticalLayout();
        oneLayout.add(new Label("tab2"));
        mapTabs.put(pasport, oneLayout);

        return pasport;

    }

    private Tab createGeneralTab() {
        Tab tab = new Tab("Главное");
      //  Div pageOne = new Div();
        VerticalLayout oneLayout = new VerticalLayout();

        VerticalLayout subOneLayoutV = new VerticalLayout();
        HorizontalLayout subOneLayoutH = new HorizontalLayout();

        TextField dateOfTakeToBalanse = new TextField("Дата приема на баланс");
        //TODO можно добавить пикер даты
        Checkbox decommissioned = new Checkbox("Списан");
        TextField dateOfdecommissioned = new TextField("Дата списания");
        dateOfdecommissioned.setEnabled(false);
        Checkbox fauly = new Checkbox("неисправный");
        subOneLayoutH.add(dateOfTakeToBalanse, decommissioned, dateOfdecommissioned);
        subOneLayoutV.add(subOneLayoutH, fauly);

        oneLayout.add(subOneLayoutV);


        TextField podrazdelenieOrGarage = new TextField("Подразделение (гараж)");
        TextField colonna = new TextField("Коллона");
        oneLayout.add(podrazdelenieOrGarage, colonna);

        VerticalLayout subTwoLayoutV = new VerticalLayout();
        TextField numberOfGarage = new TextField("Гаражный номер");
        TextField numberOfInventar = new TextField("Инвентаризационный номер");
        subTwoLayoutV.add(numberOfGarage, numberOfInventar);
        oneLayout.add(subTwoLayoutV);

        TextField comment = new TextField("Комментарий");
        TextField typeOfFuel = new TextField("Вид топлива");
        oneLayout.add(comment, typeOfFuel);

        VerticalLayout subThreeLayoutV = new VerticalLayout();
        TextField mileage = new TextField("Пробег");
        TextField dateOfMileage = new TextField("Дата пробега");
        subThreeLayoutV.add(mileage, dateOfMileage);
        oneLayout.add(subThreeLayoutV);


        TextField mashineHours = new TextField("Моточасы кран/доп.об");
        oneLayout.add(mashineHours);
        oneLayout.setVisible(true);
        add(oneLayout);
       // pageOne.add(oneLayout);
       // pageOne.setText("defef");
       // pageOne.setVisible(true);
        mapTabs.put(tab, oneLayout);

        return tab;
    }
}

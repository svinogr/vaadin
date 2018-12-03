package com.example.demo.views;

import com.example.demo.IdViewable;
import com.example.demo.entity.Selectable;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractMiddleView extends VerticalLayout implements IdViewable {
    private MenuInterface menuInterface;
    private GridInterface gridInterface;
    private Button searchBtn;

    public AbstractMiddleView(MenuInterface menuInterface, GridInterface gridInterface) {
        this.menuInterface = menuInterface;
        this.gridInterface = gridInterface;
        searchBtn = new Button(VaadinIcon.SEARCH.create());

        HorizontalLayout searchLayout = new HorizontalLayout();
        if(menuInterface instanceof Component) {
            Component component = (Component) menuInterface;
            searchLayout.add(component, searchBtn);
            searchLayout.setAlignSelf(Alignment.STRETCH, component);

        }
        add(searchLayout);

        if(gridInterface instanceof Component){
            Component component = (Component) gridInterface;
        add(component);
        }

        searchBtn.addClickListener(e->{
           MyFilterItem myFilterItem =  menuInterface.getFilterItem();
           gridInterface.searchByFilterItem(myFilterItem);
        });
    }

    public Selectable getSelectItem(){
        return gridInterface.getSelectedItem();
    }
}

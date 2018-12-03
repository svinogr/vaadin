package com.example.demo.views.personalview;

import com.example.demo.views.AbstractMiddleView;
import com.example.demo.views.GridInterface;
import com.example.demo.views.MenuInterface;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class PersonalViewNew extends AbstractMiddleView {
    public final static String ID_VIEW = "PERSONAL_VIEW";

    public PersonalViewNew( PersonalMenuView menuView, PersonalGridView gridInterface) {
        super(menuView, gridInterface);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }
}

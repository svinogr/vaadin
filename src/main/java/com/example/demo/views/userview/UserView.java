package com.example.demo.views.userview;

import com.example.demo.download.excel.UserExcelItem;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserView extends AbstractMiddleView {
    public final static String ID_VIEW = "USER_VIEW";

    public UserView(UserMenuView menuInterface, UserGridView gridInterface, UserExcelItem downloadedable) {
        super(menuInterface, gridInterface, downloadedable);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    protected void disableExcelBtn(boolean enabled) {
        toExcelBtn.setEnabled(false);
        toExcelBtn.getElement().removeAttribute("href");
    }
}


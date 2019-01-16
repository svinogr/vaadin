package com.example.demo.views;

import com.example.demo.download.Downloadedable;
import com.example.demo.entity.Selectable;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.StreamResourceWriter;
import com.vaadin.flow.server.VaadinSession;

import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractMiddleView extends VerticalLayout implements IdViewable, ValidationAction {
    protected MenuInterface menuInterface;
    protected GridInterface gridInterface;
    protected Downloadedable downloadedable;
    private Button searchBtn;
    protected Anchor toExcelBtn;
    protected HorizontalLayout btnLayout;

    public AbstractMiddleView(MenuInterface menuInterface, GridInterface gridInterface, Downloadedable downloadedable) {
        this.menuInterface = menuInterface;
        this.gridInterface = gridInterface;
        this.downloadedable = downloadedable;

        menuInterface.setValidationAction(this);

        btnLayout = new HorizontalLayout();
        searchBtn = new Button(VaadinIcon.SEARCH.create());

        toExcelBtn = new Anchor(getStream(), "");
        toExcelBtn.getElement().setAttribute("download", true);

        Button forAnchor = new Button("Экспорт Excell");
        toExcelBtn.add(forAnchor);

        btnLayout.add(searchBtn, toExcelBtn);
        addAdditionalButtons();

        disableExcelBtn(true);
        btnLayout.setPadding(true);
        btnLayout.setWidth("auto");

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setAlignItems(Alignment.END);

        if (menuInterface instanceof Component) {
            Component component = (Component) menuInterface;
            searchLayout.add(component, btnLayout);
        }
        add(searchLayout);

        if (gridInterface instanceof Component) {
            Component component = (Component) gridInterface;
            add(component);
        }

        searchBtn.addClickListener(e -> {
            MyFilterItem myFilterItem = getMyFilterItem();
            gridInterface.searchByFilterItem(myFilterItem);
        });
    }

    @Override
    public void disableComponent(boolean enabled) {
        btnLayout.setEnabled(enabled);
        disableExcelBtn(enabled);
    }


    /**
     * использовать для добавления доп кнопок для меню
     */
    protected void addAdditionalButtons() {

    }
    protected void disableExcelBtn(boolean enabled) {
        toExcelBtn.setEnabled(enabled);
        if (enabled) {
            toExcelBtn.setHref(getStream());
        } else toExcelBtn.getElement().removeAttribute("href");
    }

    private byte[] createResourse() {
        MyFilterItem myFilterItem = getMyFilterItem();
        return downloadedable.getBytesByFilterItem(myFilterItem);
    }

    protected MyFilterItem getMyFilterItem() {
        MyFilterItem myFilterItem = menuInterface.getFilterItem();
        return myFilterItem;
    }

    public Selectable getSelectItem() {
        return gridInterface.getSelectedItem();
    }

    protected StreamResource getStream() {
        return new StreamResource("file.xls",
                new StreamResourceWriter() {
                    @Override
                    public void accept(OutputStream outputStream, VaadinSession vaadinSession) throws IOException {
                        outputStream.write(createResourse());
                    }
                });
    }

}

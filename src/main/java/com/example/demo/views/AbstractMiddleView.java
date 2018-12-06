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
import com.vaadin.flow.server.StreamResourceWriter;
import com.vaadin.flow.server.VaadinSession;

import java.io.*;

public abstract class AbstractMiddleView extends VerticalLayout implements IdViewable, ValidationAction{
    protected MenuInterface menuInterface;
    protected GridInterface gridInterface;
    protected Downloadedable downloadedable;
    private Button searchBtn;
    private  Anchor toExcelBtn;
    private HorizontalLayout btnLayout;
    public AbstractMiddleView(MenuInterface menuInterface, GridInterface gridInterface, Downloadedable downloadedable) {
        this.menuInterface = menuInterface;
        this.gridInterface = gridInterface;
        this.downloadedable = downloadedable;

        menuInterface.setValidationAction(this);

        btnLayout = new HorizontalLayout();
        searchBtn = new Button(VaadinIcon.SEARCH.create());

        toExcelBtn = new Anchor(new com.vaadin.flow.server.StreamResource("file.xls",
                new StreamResourceWriter() {
            @Override
            public void accept(OutputStream outputStream, VaadinSession vaadinSession) throws IOException {
                outputStream.write(createResourse());
            }
        }), "");
        toExcelBtn.getElement().setAttribute("download", true);
        toExcelBtn.add(new Button("Excell"));

        btnLayout.add(searchBtn, toExcelBtn);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setAlignItems(Alignment.END);

        if(menuInterface instanceof Component) {
            Component component = (Component) menuInterface;
           searchLayout.add(component, btnLayout);
        }
        add(searchLayout);

        if(gridInterface instanceof Component){
            Component component = (Component) gridInterface;
        add(component);
        }


        searchBtn.addClickListener(e->{
           MyFilterItem myFilterItem = getMyFilterItem();
           gridInterface.searchByFilterItem(myFilterItem);
        });


    }

    @Override
    public void disableComponent(boolean enabled) {
        searchBtn.setEnabled(enabled);
       // toExcelBtn.setVisible(enabled);
        btnLayout.setEnabled(enabled);
    }

    private byte[] createResourse() {
        MyFilterItem myFilterItem = menuInterface.getFilterItem();
        System.out.println(myFilterItem);
        return downloadedable.getBytesByFilterItem(myFilterItem);
//        Workbook book = new HSSFWorkbook();
//        Sheet sheet = book.createSheet("Имя листа");
//        Row row = sheet.createRow(0);
//
//        Cell cellOne = row.createCell(0);
//        cellOne.setCellValue("первая");
//
//
//        Cell cellOne2 = row.createCell(0);
//        cellOne2.setCellValue("вторая");
//
//        return ((HSSFWorkbook) book).getBytes();


    }

    protected MyFilterItem getMyFilterItem(){
        MyFilterItem myFilterItem =  menuInterface.getFilterItem();
        return myFilterItem;
    }

    public Selectable getSelectItem(){
        return gridInterface.getSelectedItem();
    }


}

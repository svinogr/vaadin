package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.upload.editor.CarUploadEditor;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.poi.ss.usermodel.Workbook;

import static com.vaadin.flow.shared.ui.Transport.LONG_POLLING;

@SpringComponent
@UIScope
@Push(transport = LONG_POLLING)
@Route("push")
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";
    private ProgressBar bar;
    private Workbook workbook;

    public CarView(CarMenuView carMenu, CarGridView carGridView, CarExcelItem carExcelItem, CarUploadEditor carUploadEditor) {
        super(carMenu, carGridView, carExcelItem, carUploadEditor);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    @Override
    protected void addAdditionalButtons() {
        Button uploadButton = new Button("Импорт из Excell");
        uploadButton.addClickListener((e) -> {
            getUI().get().getPushConfiguration().setPushMode(PushMode.AUTOMATIC); //ставим работу push, с анотациями не работает почему то
            showUploadDialog();
        });
        btnLayout.add(uploadButton);
    }

    private void showUploadDialog() {
        Dialog dialog = new Dialog();
        System.out.println(UI.getCurrent());
        abstractUndoableEdit.setChangeHandler(() -> {
            dialog.close();
            MyFilterItem filterItem = menuInterface.getFilterItem();
            gridInterface.searchByFilterItem(filterItem);
        });
        dialog.add(abstractUndoableEdit);
        //  dialog.setHeight("500px");
        dialog.setHeight("270px");
        dialog.setWidth("450px");
        dialog.open();
        dialog.setCloseOnOutsideClick(false);
    }

}

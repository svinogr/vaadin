package com.example.demo.views;

import com.example.demo.download.Downloadedable;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.ParentSearch;
import com.example.demo.services.search.Parentable;
import com.example.demo.upload.Uploadable;

public abstract class AbstractMiddleByParentView extends AbstractMiddleView implements SelectByParent {
    protected long parentId;

    public AbstractMiddleByParentView(MenuInterface menuInterface, GridInterface gridInterface, Downloadedable downloadedable, Uploadable uploadable) {
        super(menuInterface, gridInterface, downloadedable, uploadable);
    }

    protected abstract MyFilterItem getDefaultMyFilterItem();

    @Override
    public void updateByParent(long id) {
        this.parentId = id;
        MyFilterItem myFilterItem = getDefaultMyFilterItem();
        setupDefaultValueForDefaulFilterItem(myFilterItem);
        gridInterface.searchByFilterItem(myFilterItem);
    }

    @Override
    protected MyFilterItem getMyFilterItem() {
        MyFilterItem myFilterItem = menuInterface.getFilterItem();

        if (myFilterItem == null) {
            myFilterItem = getDefaultMyFilterItem();
            setupDefaultValueForDefaulFilterItem(myFilterItem);
        } else {
            setupDefaultValueForDefaulFilterItem(myFilterItem);
        }

        return myFilterItem;
    }

    private void setupDefaultValueForDefaulFilterItem(MyFilterItem myFilterItem) {
        Parentable parentSearch = new ParentSearch(parentId);
        myFilterItem.setParentable(parentSearch);
    }


}

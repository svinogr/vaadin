package com.example.demo.views;

import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.ParentSearch;
import com.example.demo.services.search.Parentable;

public abstract class AbstractMiddleByParentView extends AbstractMiddleView implements SelectByParent {
    protected long parentId;

    public AbstractMiddleByParentView(MenuInterface menuInterface, GridInterface gridInterface) {
        super(menuInterface, gridInterface);
    }

    protected abstract MyFilterItem getDefaiultMyFilterItem();

    @Override
    public void updateByParent(long id) {
        this.parentId = id;
        MyFilterItem myFilterItem = getDefaiultMyFilterItem();
        setupDefaultValueForDefaulFilterItem(myFilterItem);
        gridInterface.searchByFilterItem(myFilterItem);

    }

    @Override
    protected MyFilterItem getMyFilterItem() {
        MyFilterItem myFilterItem = menuInterface.getFilterItem();

        if(myFilterItem == null){
            myFilterItem = getDefaiultMyFilterItem();
           setupDefaultValueForDefaulFilterItem(myFilterItem);
        }

        return myFilterItem;
    }

    private void setupDefaultValueForDefaulFilterItem(MyFilterItem myFilterItem){
        Parentable parentSearch = new ParentSearch(parentId);
        myFilterItem.setParentable(parentSearch);
    }


}

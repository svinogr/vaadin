package com.example.demo.services.search;

public class ParentSearch implements Parentable {
    private long parentId;

    public ParentSearch(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public long getParentIdForSearch() {
        return parentId;
    }
}

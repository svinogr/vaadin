package com.example.demo.services;

import com.example.demo.entity.jornal.JournalItem;

import java.util.List;

public interface JournalService extends ItemService<JournalItem> {
    int getCountByParentId(long parentId);

    List<JournalItem> findAllByParentId(long ParentId, int offset, int limit);

}

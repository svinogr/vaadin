package com.example.demo.services;

import com.example.demo.entity.jornal.JournalItem;

import java.util.List;

public interface JournalService {
    int getCountByParentId(long parentId);
    List<JournalItem> findAllByParentId(long ParentId, int offset, int limit);
    JournalItem create(JournalItem journalItem);
    JournalItem update(JournalItem journalItem);
    boolean delete(JournalItem journalItem);
    JournalItem getById(long id);
}

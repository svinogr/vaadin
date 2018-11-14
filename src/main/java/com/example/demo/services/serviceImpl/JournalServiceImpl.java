package com.example.demo.services.serviceImpl;

import com.example.demo.dao.JournalRepository;
import com.example.demo.entity.cars.utils.search.JournalSpecification;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalRepository journalRepository;

    @Override
    public int getCountByParentId(long parentId) {
        Specification<JournalItem> specification = JournalSpecification.getByIdParent(parentId);
        return Math.toIntExact(journalRepository.count(specification));

    }

    @Override
    public List<JournalItem> findAllByParentId(long parentId, int offset, int limit) {
        List<JournalItem> list = Collections.emptyList();
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Specification<JournalItem> specification = JournalSpecification.getByIdParent(parentId);
        List<JournalItem> content = journalRepository.findAll(specification, pageable).getContent();

        if (content != null) {
            list = content;
        }
        return list;

    }

    @Override
    public JournalItem create(JournalItem journalItem) {
     return journalRepository.save(journalItem);
    }

    @Override
    public JournalItem update(JournalItem journalItem) {
        return journalRepository.save(journalItem);
    }

    @Override
    @Transactional
    public boolean delete(JournalItem journalItem) {
        journalRepository.delete(journalItem);
        return  true;
    }

    @Override
    public JournalItem getById(long id) {
        return journalRepository.findById(id).get();
    }

}

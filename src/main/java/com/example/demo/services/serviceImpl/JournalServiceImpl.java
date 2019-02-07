package com.example.demo.services.serviceImpl;

import com.example.demo.dao.JournalRepository;
import com.example.demo.entity.jornal.EnumColumnNameForJournal;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import com.example.demo.services.LoginService;
import com.example.demo.services.search.JournalSpecification;
import com.example.demo.services.search.MyFilterItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    LoginService loginService;


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
        journalItem.setChanged(whoCnanged());
        return journalRepository.save(journalItem);
    }

    @Override
    public JournalItem update(JournalItem journalItem) {
        journalItem.setChanged(whoCnanged());
        return journalRepository.save(journalItem);
    }

    @Override
    @Transactional
    public boolean delete(JournalItem journalItem) {
        journalRepository.delete(journalItem);
        return true;
    }

    @Override
    public List<JournalItem> saveList(List<JournalItem> list) {
        return list;
    }

    private String whoCnanged() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(loginService.getAuth().getUsername());
        stringBuilder.append(" ");
        stringBuilder.append(new Date());
        return stringBuilder.toString();
    }

    @Override
    public List<JournalItem> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<JournalItem> resulList;
        Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<JournalItem> specification = createSpecification(myFilterItem.get());
            resulList = journalRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = journalRepository.findAll(pageable).getContent();
        }
        return resulList;
    }

    private Specification<JournalItem> createSpecification(MyFilterItem myFilterItem) {
        Specification<JournalItem> specification = null;
        EnumColumnNameForJournal enumColumnNameForJournal = (EnumColumnNameForJournal) myFilterItem.getEnumColumnNamesFor();
        if (enumColumnNameForJournal == null) {
            return JournalSpecification.getByIdParent(myFilterItem.getParentIdForSearch());
        }

        switch (enumColumnNameForJournal) {
            case CLOSED:
                specification = JournalSpecification.getByClosed(myFilterItem);
                break;

            default:
                System.out.println("не удалсоь найти спецификацию");
        }
        return specification;

    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;

        if (myFilterItem.isPresent()) {
            Specification<JournalItem> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(journalRepository.count(specification));
        } else {
            count = Math.toIntExact(journalRepository.count());
        }
        return count;
    }

    @Override
    public List<JournalItem> findByExampleWithoutPagable(Optional<MyFilterItem> myFilterItem) {
        List<JournalItem> resulList;
        if (myFilterItem.isPresent()) {
            Specification<JournalItem> specification = createSpecification(myFilterItem.get());
            resulList = journalRepository.findAll(specification);
            return resulList;
        } else {
            resulList = journalRepository.findAll();
        }
        return resulList;
    }

    @Override
    public JournalItem getById(long id) {
        Optional<JournalItem> journalItem = journalRepository.findById(id);
        if (journalItem.isPresent()) {
            return journalItem.get();
        } else return null;

    }

}

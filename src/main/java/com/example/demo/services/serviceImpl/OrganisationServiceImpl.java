package com.example.demo.services.serviceImpl;

import com.example.demo.dao.OrganisationRepository;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.LoginService;
import com.example.demo.services.OrganisationService;
import com.example.demo.services.pageable.MyOffsetPageable;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.OrganisationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.views.AbstractGridView.QUANTITY;

@Service
public class OrganisationServiceImpl implements OrganisationService {
    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    LoginService loginService;

    @Override
    public int getCountByParentId(long parentId) {
        return 0;
    }

    @Override
    public List<Organisation> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<Organisation> resulList;

        int page;
        if (offset == 0) {
            page = 0;

        } else {
            page = offset / QUANTITY;
        }

        Pageable pageable = new MyOffsetPageable(page, limit, Sort.by(Sort.Direction.ASC, "id"), offset);

        if (myFilterItem.isPresent()) {
            Specification<Organisation> specification = createSpecification(myFilterItem.get());
            resulList = organisationRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = organisationRepository.findAll(pageable).getContent();
        }
        return resulList;
    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;

        if (myFilterItem.isPresent()) {
            Specification<Organisation> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(organisationRepository.count(specification));
        } else {
            count = Math.toIntExact(organisationRepository.count());
        }
        return count;
    }

    @Override
    public List<Organisation> findByExampleWithoutPagable(Optional<MyFilterItem> myFilterItem) {
        List<Organisation> resulList;
        if (myFilterItem.isPresent()) {
            Specification<Organisation> specification = createSpecification(myFilterItem.get());
            resulList = organisationRepository.findAll(specification);
            return resulList;
        } else {
            resulList = organisationRepository.findAll();
        }
        return resulList;
    }

    @Override
    public List<Organisation> findAllByParentId(long parentId, int offset, int limit) {
        return null;
    }

    @Override
    public Organisation create(Organisation organisation) {
        organisation.setChanged(whoCnanged());
        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation update(Organisation organisation) {
        organisation.setChanged(whoCnanged());
        return organisationRepository.save(organisation);
    }

    @Override
    public boolean delete(Organisation organisation) {
        organisationRepository.delete(organisation);
        return true;
    }

    @Override
    public List<Organisation> saveList(List<Organisation> list) {
        return list;
    }

    @Override
    public Organisation getById(long id) {
        Optional<Organisation> organisation = organisationRepository.findById(id);
        if (organisation.isPresent()) {
            return organisation.get();
        } else return null;
    }

    private String whoCnanged() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(loginService.getAuth().getUsername());
        stringBuilder.append(" ");
        stringBuilder.append(new Date());
        return stringBuilder.toString();
    }

    private Specification<Organisation> createSpecification(MyFilterItem myFilterItem) {
        Specification<Organisation> specification = null;
        EnumColumnNameForOrg enumColumnNameForOrg = (EnumColumnNameForOrg) myFilterItem.getEnumColumnNamesFor();
        switch (enumColumnNameForOrg) {
            case NAME:
                specification = OrganisationSpecification.getByName(myFilterItem);
                break;
            case OGRN:
                specification = OrganisationSpecification.getByOgrn(myFilterItem);
                break;
            case OKPO:
                specification = OrganisationSpecification.getByOkpo(myFilterItem);
                break;
            case EGRUL:
                specification = OrganisationSpecification.getByEgrul(myFilterItem);
                break;
            case INN:
                specification = OrganisationSpecification.getByInn(myFilterItem);
                break;
            case KPP:
                specification = OrganisationSpecification.getByKpp(myFilterItem);
                break;
            default:
        }
        return specification;
    }
}

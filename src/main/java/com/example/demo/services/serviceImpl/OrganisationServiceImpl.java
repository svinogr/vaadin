package com.example.demo.services.serviceImpl;

import com.example.demo.dao.OrganisationRepository;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.OrganisationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public int getCountByParentId(long parentId) {
        return 0;
    }

    @Override
    public List<Organisation> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<Organisation> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<Organisation> specification = createSpecification(myFilterItem.get());
            resulList = organisationRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = organisationRepository.findAll();
        }
        System.out.println(resulList.size()+"razmer");
        return resulList;
    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;

        if (myFilterItem.isPresent()) {
            Specification<Organisation> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(organisationRepository.count(specification));
        }else {
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
        System.out.println(resulList.size()+"razmer");
        return resulList;
    }

    @Override
    public List<Organisation> findAllByParentId(long parentId, int offset, int limit) {
//        List<Organisation> organisations = Collections.emptyList();
//        Pageable page = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
//
//        Specification<Organisation> specification = OrganisationSpecification.getByIdParent(parentId);
//        organisations = organisationRepository.findAll(specification, page).getContent();
//        return organisations;
        return null;
    }

    @Override
    public Organisation create(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation update(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public boolean delete(Organisation organisation) {
        organisationRepository.delete(organisation);
        return true;
    }

    @Override
    public Organisation getById(long id) {
        return organisationRepository.findById(id).get();
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
                System.out.println("не удалсоь найти спецификацию");
        }
        return specification;
    }
}

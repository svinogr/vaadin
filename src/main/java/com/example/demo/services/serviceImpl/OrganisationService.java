package com.example.demo.services.serviceImpl;

import com.example.demo.dao.OrganisationRepository;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.OrganisationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrganisationService implements com.example.demo.services.OrganisationService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public int getCountByParentId(long parentId) {
        return 0;
    }

    @Override
    public List<Organisation> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        return null;
    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        return 0;
    }

    @Override
    public List<Organisation> findAllByParentId(long ParentId, int offset, int limit) {
        return null;
    }

    @Override
    public Organisation create(Organisation person) {
        return null;
    }

    @Override
    public Organisation update(Organisation person) {
        return null;
    }

    @Override
    public boolean delete(Organisation person) {
        return false;
    }

    @Override
    public Organisation getById(long id) {
        return null;
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

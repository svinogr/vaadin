package com.example.demo.services.serviceImpl;

import com.example.demo.dao.PersonRepository;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.PersonSpecification;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;


    @Override
    public int getCountByParentId(long parentId) {
        Specification<Person> specification = PersonSpecification.getByIdParent(parentId);
        int count = Math.toIntExact(personRepository.count(specification));
        return count;
    }

    @Override
    public List<Person> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<Person> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<Person> specification = createSpecification(myFilterItem.get());
            resulList = personRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = personRepository.findAll();
        }
        System.out.println(resulList.size()+"razmer");
        return resulList;
    }

    private Specification<Person> createSpecification(MyFilterItem myFilterItem) {
        Specification<Person> specification = null;
        EnumColumnNamesForPerson enumColumnNamesForPerson = (EnumColumnNamesForPerson) myFilterItem.getEnumColumnNamesForCar();
        switch (enumColumnNamesForPerson) {
            case DATE_OF_BIRTH:
                specification = PersonSpecification.getByDateBirth(myFilterItem);
                break;
            case SURNAME:
                break;
            case FIRED:
                break;
            case TYPE_PERSON:
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
            Specification<Person> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(personRepository.count(specification));
        }else {
            count = Math.toIntExact(personRepository.count());
        }
        return count;
    }

    @Override
    public List<Person> findAllByParentId(long parentId, int offset, int limit) {
        List<Person> persons = Collections.emptyList();
        Pageable page = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        Specification<Person> specification = PersonSpecification.getByIdParent(parentId);

        persons = personRepository.findAll(specification, page).getContent();
        return persons;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean delete(Person person) {
        personRepository.delete(person);
        return true;
    }

    @Override
    public Person getById(long id) {
        return personRepository.findById(id).get();
    }
}

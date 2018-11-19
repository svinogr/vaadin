package com.example.demo.services.serviceImpl;

import com.example.demo.dao.PersonRepository;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.cars.utils.search.PersonSpecification;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
    public int getCount() {
        return Math.toIntExact(personRepository.count());
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
    public List<Person> findAll() {
        return personRepository.findAll();
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

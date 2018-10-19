package com.example.demo.entity.cars;

import com.example.demo.entity.cars.car.Car;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private  EnumTypePerson enumTypePerson;

    @Column(name = "technics")
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "id_tecnhics",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "technics_id") }

    )
    private List<Car> cars;




    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.demo.entity.cars.personal;

import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.car.Car;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "person")
public class Person implements Selectable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "changed")
    private String changed;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "date_order")
    private Date dateOfOrder;

    @Column(name = "order_name")
    private String order;

    @Column(name = "fired")
    private boolean fired;

    @Column(name = "position")
    private String position;

    @Column(name = "number_tabel")
    private String numberOfTabel;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "comment")
    private String comment;

    @Enumerated(value = EnumType.STRING)
    private  EnumTypePerson enumTypePerson;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_person",
            joinColumns =  @JoinColumn(name = "person_id") ,
            inverseJoinColumns =  @JoinColumn(name = "car_id") )

    private List<Car> car  = new ArrayList<>();

    public Person() {
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumberOfTabel() {
        return numberOfTabel;
    }

    public void setNumberOfTabel(String numberOfTabel) {
        this.numberOfTabel = numberOfTabel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public EnumTypePerson getEnumTypePerson() {
        return enumTypePerson;
    }

    public void setEnumTypePerson(EnumTypePerson enumTypePerson) {
        this.enumTypePerson = enumTypePerson;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}

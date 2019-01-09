package com.example.demo.entity.cars.car;


import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.personal.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car implements Selectable {
    public static final String ID = "id";
    public static final String ID_PASSPORT_DATA = "id_passport_data";
    public static final String TRACK = "track";
    public static final String ID_GENERAL_DATA = "id_general_data";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Car() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ID_PASSPORT_DATA)
    private PassportData passportData;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "changed")
    private String changed;

//    @ManyToOne
//    @JoinColumn(name = "id_owner")
//    private Owner owner;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_person",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))

    private List<Person> persons = new ArrayList<>();

    @Column(name = TRACK)
    private boolean track;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ID_GENERAL_DATA)
    private GeneralData generalData;

    //private AdditionalData additionalData;
    // private List<Driver> listDriver;
    // private List<Photo> listPhoto;
    //  private ReglamentTO reglamentTO;
    //  private ChangeData changeData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


//    private List<Trailer> trailers;
    //private Tahograf tahograf;
    //private Platon platon;

    //    private Contract contract;
//
    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

//    public Owner getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

//    public Set<Person> getPerson() {
//        return persons;
//    }
//
//    public void setPerson(Set<Person> person) {
//        this.persons = person;
//    }

    public boolean isTrack() {
        return track;
    }

    public void setTrack(boolean track) {
        this.track = track;
    }


    public static String getID() {
        return ID;
    }

    public static String getIdPassportData() {
        return ID_PASSPORT_DATA;
    }

    public static String getTRACK() {
        return TRACK;
    }

    public static String getIdGeneralData() {
        return ID_GENERAL_DATA;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", passportData=" + //passportData.getId() +
                ", owner=" + //owner.getId() +
                ", generalData=" + generalData.getId() +
                '}';
    }
}





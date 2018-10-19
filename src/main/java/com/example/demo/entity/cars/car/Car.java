package com.example.demo.entity.cars.car;


import com.example.demo.entity.cars.Person;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Car() {
    }

   // @OneToOne(cascade = CascadeType.ALL)
  //  @JoinColumn(name = "id_passport_data")
 //   private PassportData passportData;

//    @ManyToOne
//    @JoinColumn(name = "id_owner")
//    private Owner owner;

    @ManyToMany(mappedBy = "cars" )
    private List<Person> person;

    @Column(name = "track")
    private boolean track;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_general_data")
     private GeneralData generalData;

    @ManyToMany(mappedBy = "cars")
    private List<Person> persons;
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
 //   public PassportData getPassportData() {
   //     return passportData;
   // }

    //public void setPassportData(PassportData passportData) {
      //  this.passportData = passportData;
   // }

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

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public boolean isTrack() {
        return track;
    }

    public void setTrack(boolean track) {
        this.track = track;
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





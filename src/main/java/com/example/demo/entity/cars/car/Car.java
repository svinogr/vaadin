package com.example.demo.entity.cars.car;


import com.example.demo.entity.cars.owner.Owner;

import javax.persistence.*;

@Entity()
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Car() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_passport_data")
    private PassportData passportData;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_owner")
    private Owner owner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_general_data")
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

    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }
}

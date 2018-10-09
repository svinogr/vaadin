package com.example.demo.entity.cars.car;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "passport_data")
public class PassportData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "passportData")
    private Car car;

    @Column(name = "vin")
    private String vin;

    @Column(name = "modelTS")
    private String modelTS;

    @Column(name = "typeTS")
    private String typeTS;

    @Column(name = "category")
    private String category;

    @Column(name = "yearOfBuild")
    private Date yearOfBuild;//дата выпуска

    @Column(name = "modelOfEngine")
    private String modelOfEngine;

    @Column(name = "eccoClass")
    private int eccoClass;

    @Column(name = "numberOfEngine")
    private String numberOfEngine;

    @Column(name = "numberOfChassis")
    private String numberOfChassis; //номер шасси

    @Column(name = "numberOfBody")
    private String numberOfBody; //номер кузова

    @Column(name = "color")
    private String color;

    @Column(name = "powerOfEngine")
    private String powerOfEngine; // в лошадинных силах

    @Column(name = "volumeOfEngine")
    private int volumeOfEngine; //обьем двигателя

    @Column(name = "maxMass")
    private int maxMass;

    @Column(name = "maxMassWithout")
    private int maxMassWithout; //макс масса без нагрузки

    @Column(name = "builder")
    private String builder; // производитель

    @Column(name = "numberOfPassportTS")
    private String numberOfPassportTS;

    @Column(name = "dateOfPassportTS")
    private Date dateOfPassportTS;

    @Column(name = "placeOfIssuanceOfPassportTS")
    private String placeOfIssuanceOfPassportTS;

//    @ManyToOne
//    @JoinColumn(name = "id_owner")
//    private Owner owner; перенесено в Car

    @Column(name = "cost")
    private BigInteger cost;
    // @Column(name = )
    //private Group group;

    @Column(name = "regNumber")
    private String regNumber;

    @Column(name = "oldregNumber")
    private String oldregNumber;

    @Column(name = "certificateOfRegistration")
    private String certificateOfRegistration;

    @Column(name = "placeOfregistration")
    private String placeOfregistration;

    @Column(name = "dateOfRegistration")
    private Date dateOfRegistration;

    @Column(name = "tempRegistration")
    private Date tempRegistration;

    @Enumerated(value = EnumType.STRING)
    private EnumTypeOfBody typeOfBody; // сменить на енум

    @Column(name = "quantityOfPallet")
    private int quantityOfPallet;

    @Column(name = "lenghtOfBody")
    private double lenghtOfBody;

    @Column(name = "widhtOfBody")
    private double widhtOfBody;

    @Column(name = "heightOfBody")
    private double heightOfBody;

    @Column(name = "volumeOfBody")
    private double volumeOfBody;

}

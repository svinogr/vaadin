package com.example.demo.entity.cars.car;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

//@Entity
//@Table(name = "passport_data")
public class PassportData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "passportData", cascade = CascadeType.ALL)
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

    public PassportData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelTS() {
        return modelTS;
    }

    public void setModelTS(String modelTS) {
        this.modelTS = modelTS;
    }

    public String getTypeTS() {
        return typeTS;
    }

    public void setTypeTS(String typeTS) {
        this.typeTS = typeTS;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getYearOfBuild() {
        return yearOfBuild;
    }

    public void setYearOfBuild(Date yearOfBuild) {
        this.yearOfBuild = yearOfBuild;
    }

    public String getModelOfEngine() {
        return modelOfEngine;
    }

    public void setModelOfEngine(String modelOfEngine) {
        this.modelOfEngine = modelOfEngine;
    }

    public int getEccoClass() {
        return eccoClass;
    }

    public void setEccoClass(int eccoClass) {
        this.eccoClass = eccoClass;
    }

    public String getNumberOfEngine() {
        return numberOfEngine;
    }

    public void setNumberOfEngine(String numberOfEngine) {
        this.numberOfEngine = numberOfEngine;
    }

    public String getNumberOfChassis() {
        return numberOfChassis;
    }

    public void setNumberOfChassis(String numberOfChassis) {
        this.numberOfChassis = numberOfChassis;
    }

    public String getNumberOfBody() {
        return numberOfBody;
    }

    public void setNumberOfBody(String numberOfBody) {
        this.numberOfBody = numberOfBody;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPowerOfEngine() {
        return powerOfEngine;
    }

    public void setPowerOfEngine(String powerOfEngine) {
        this.powerOfEngine = powerOfEngine;
    }

    public int getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(int volumeOfEngine) {
        this.volumeOfEngine = volumeOfEngine;
    }

    public int getMaxMass() {
        return maxMass;
    }

    public void setMaxMass(int maxMass) {
        this.maxMass = maxMass;
    }

    public int getMaxMassWithout() {
        return maxMassWithout;
    }

    public void setMaxMassWithout(int maxMassWithout) {
        this.maxMassWithout = maxMassWithout;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getNumberOfPassportTS() {
        return numberOfPassportTS;
    }

    public void setNumberOfPassportTS(String numberOfPassportTS) {
        this.numberOfPassportTS = numberOfPassportTS;
    }

    public Date getDateOfPassportTS() {
        return dateOfPassportTS;
    }

    public void setDateOfPassportTS(Date dateOfPassportTS) {
        this.dateOfPassportTS = dateOfPassportTS;
    }

    public String getPlaceOfIssuanceOfPassportTS() {
        return placeOfIssuanceOfPassportTS;
    }

    public void setPlaceOfIssuanceOfPassportTS(String placeOfIssuanceOfPassportTS) {
        this.placeOfIssuanceOfPassportTS = placeOfIssuanceOfPassportTS;
    }

    public BigInteger getCost() {
        return cost;
    }

    public void setCost(BigInteger cost) {
        this.cost = cost;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getOldregNumber() {
        return oldregNumber;
    }

    public void setOldregNumber(String oldregNumber) {
        this.oldregNumber = oldregNumber;
    }

    public String getCertificateOfRegistration() {
        return certificateOfRegistration;
    }

    public void setCertificateOfRegistration(String certificateOfRegistration) {
        this.certificateOfRegistration = certificateOfRegistration;
    }

    public String getPlaceOfregistration() {
        return placeOfregistration;
    }

    public void setPlaceOfregistration(String placeOfregistration) {
        this.placeOfregistration = placeOfregistration;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getTempRegistration() {
        return tempRegistration;
    }

    public void setTempRegistration(Date tempRegistration) {
        this.tempRegistration = tempRegistration;
    }

    public EnumTypeOfBody getTypeOfBody() {
        return typeOfBody;
    }

    public void setTypeOfBody(EnumTypeOfBody typeOfBody) {
        this.typeOfBody = typeOfBody;
    }

    public int getQuantityOfPallet() {
        return quantityOfPallet;
    }

    public void setQuantityOfPallet(int quantityOfPallet) {
        this.quantityOfPallet = quantityOfPallet;
    }

    public double getLenghtOfBody() {
        return lenghtOfBody;
    }

    public void setLenghtOfBody(double lenghtOfBody) {
        this.lenghtOfBody = lenghtOfBody;
    }

    public double getWidhtOfBody() {
        return widhtOfBody;
    }

    public void setWidhtOfBody(double widhtOfBody) {
        this.widhtOfBody = widhtOfBody;
    }

    public double getHeightOfBody() {
        return heightOfBody;
    }

    public void setHeightOfBody(double heightOfBody) {
        this.heightOfBody = heightOfBody;
    }

    public double getVolumeOfBody() {
        return volumeOfBody;
    }

    public void setVolumeOfBody(double volumeOfBody) {
        this.volumeOfBody = volumeOfBody;
    }
}

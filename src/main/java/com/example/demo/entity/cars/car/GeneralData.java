package com.example.demo.entity.cars.car;


import com.example.demo.entity.cars.FinanceResponsePerson;
import com.example.demo.entity.cars.owner.Contract;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "general_data")
public class GeneralData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "generalData")
    private Car car;

    @Column(name = "dateOfTakeToBalanse")
    private Date dateOfTakeToBalanse;

    @Column(name = "decommissioned")
    private boolean decommissioned; //списан

    @Column(name = "dateOfdecommissioned")
    private Date dateOfdecommissioned;

    @Column(name = "fauly")
    private boolean fauly; //неисправный

    @Column(name = "podrazdelenieOrGarage")
    private String podrazdelenieOrGarage;

    @Column(name = "colonna")
    private String colonna;

    @Column(name = "numberOfGarage")
    private String numberOfGarage;

    @Column(name = "numberOfInventar")
    private String numberOfInventar;

    @Column(name = "comment")
    private String comment;

    @Column(name = "typeOfFuel")
    private String typeOfFuel;

    @Column(name = "mileage")
    private double mileage; // пробег

    @Column(name = "dateOfMileage")
    private Date dateOfMileage;

    @Column(name = "mashineHours")
    private int mashineHours;

//    private List<Trailer> trailers;
//
//    private FinanceResponsePerson financeResponcePerson;
//    private Contract contract;
//    private BigInteger priceOfContract;
//
//    private String license;
//    private Date limiteOfLicense;
//
//    private String numberOfTahograf;
//    private String modelOfTahograf;
//    private Date verificationLimit;
//    private Date calibrationLimit;
//    private String platon;


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

    public Date getDateOfTakeToBalanse() {
        return dateOfTakeToBalanse;
    }

    public void setDateOfTakeToBalanse(Date dateOfTakeToBalanse) {
        this.dateOfTakeToBalanse = dateOfTakeToBalanse;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }

    public Date getDateOfdecommissioned() {
        return dateOfdecommissioned;
    }

    public void setDateOfdecommissioned(Date dateOfdecommissioned) {
        this.dateOfdecommissioned = dateOfdecommissioned;
    }

    public boolean isFauly() {
        return fauly;
    }

    public void setFauly(boolean fauly) {
        this.fauly = fauly;
    }

    public String getPodrazdelenieOrGarage() {
        return podrazdelenieOrGarage;
    }

    public void setPodrazdelenieOrGarage(String podrazdelenieOrGarage) {
        this.podrazdelenieOrGarage = podrazdelenieOrGarage;
    }

    public String getColonna() {
        return colonna;
    }

    public void setColonna(String colonna) {
        this.colonna = colonna;
    }

    public String getNumberOfGarage() {
        return numberOfGarage;
    }

    public void setNumberOfGarage(String numberOfGarage) {
        this.numberOfGarage = numberOfGarage;
    }

    public String getNumberOfInventar() {
        return numberOfInventar;
    }

    public void setNumberOfInventar(String numberOfInventar) {
        this.numberOfInventar = numberOfInventar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public Date getDateOfMileage() {
        return dateOfMileage;
    }

    public void setDateOfMileage(Date dateOfMileage) {
        this.dateOfMileage = dateOfMileage;
    }

    public int getMashineHours() {
        return mashineHours;
    }

    public void setMashineHours(int mashineHours) {
        this.mashineHours = mashineHours;
    }

//    public List<Trailer> getTrailers() {
//        return trailers;
//    }
//
//    public void setTrailers(List<Trailer> trailers) {
//        this.trailers = trailers;
//    }
//
//    public FinanceResponsePerson getFinanceResponcePerson() {
//        return financeResponcePerson;
//    }
//
//    public void setFinanceResponcePerson(FinanceResponsePerson financeResponcePerson) {
//        this.financeResponcePerson = financeResponcePerson;
//    }
//
//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }
//
//    public BigInteger getPriceOfContract() {
//        return priceOfContract;
//    }
//
//    public void setPriceOfContract(BigInteger priceOfContract) {
//        this.priceOfContract = priceOfContract;
//    }
//
//    public String getLicense() {
//        return license;
//    }
//
//    public void setLicense(String license) {
//        this.license = license;
//    }
//
//    public Date getLimiteOfLicense() {
//        return limiteOfLicense;
//    }
//
//    public void setLimiteOfLicense(Date limiteOfLicense) {
//        this.limiteOfLicense = limiteOfLicense;
//    }
//
//    public String getNumberOfTahograf() {
//        return numberOfTahograf;
//    }
//
//    public void setNumberOfTahograf(String numberOfTahograf) {
//        this.numberOfTahograf = numberOfTahograf;
//    }
//
//    public String getModelOfTahograf() {
//        return modelOfTahograf;
//    }
//
//    public void setModelOfTahograf(String modelOfTahograf) {
//        this.modelOfTahograf = modelOfTahograf;
//    }
//
//    public Date getVerificationLimit() {
//        return verificationLimit;
//    }
//
//    public void setVerificationLimit(Date verificationLimit) {
//        this.verificationLimit = verificationLimit;
//    }
//
//    public Date getCalibrationLimit() {
//        return calibrationLimit;
//    }
//
//    public void setCalibrationLimit(Date calibrationLimit) {
//        this.calibrationLimit = calibrationLimit;
//    }
//
//    public String getPlaton() {
//        return platon;
//    }
//
//    public void setPlaton(String platon) {
//        this.platon = platon;
//    }
}

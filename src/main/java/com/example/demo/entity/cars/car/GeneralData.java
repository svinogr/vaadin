package com.example.demo.entity.cars.car;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "general_data")
public class GeneralData {
    public static final String DATE_OF_TAKE_TO_BALLANCE = "dateOfTakeToBalanse";
    public static final String DECOMISSIONED = "decommissioned";
    public static final String DATE_OF_COMMISSIONED = "dateOfdecommissioned";
    public static final String FAULY = "fauly";
    public static final String PODRAZDELENIE_OR_GARAGE = "podrazdelenieOrGarage";
    public static final String COLONNA = "colonna";
    public static final String NUMBER_OF_GARAGE = "numberOfGarage";
    public static final String NUMBER_OF_INVENTAR = "numberOfInventar";
    public static final String COMMENT = "comment";
    public static final String TYPE_OF_FUEL = "typeOfFuel";
    public static final String MILEAGE = "mileage";
    public static final String DATE_OF_MILEAGE = "dateOfMileage";
    public static final String MASHIN_HOURS = "mashineHours";
    public static final String NUMBER_OF_TAHOGRAF = "numberOfTahograf";
    public static final String MODEL_TAHOGRAF = "modelOfTahograf";
    public static final String DATE_POVERKA_OF_TAHOGRAF = "dateOfPoverkaTahograf";
    public static final String DATE_CALIBR_OF_TAHOGRAF = "dateOfCalibrTahograf";
    public static final String PLATON = "platon";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "generalData", cascade = CascadeType.ALL)
    private Car car;

    @Column(name = DATE_OF_TAKE_TO_BALLANCE)
    private Date dateOfTakeToBalanse;

    @Column(name = DECOMISSIONED)
    private boolean decommissioned; //списан

    @Column(name = DATE_OF_COMMISSIONED)
    private Date dateOfdecommissioned;

    @Column(name = FAULY)
    private boolean fauly; //неисправный

    @Column(name = PODRAZDELENIE_OR_GARAGE)
    private String podrazdelenieOrGarage;

    @Column(name = COLONNA)
    private String colonna;

    @Column(name = NUMBER_OF_GARAGE)
    private String numberOfGarage;

    @Column(name = NUMBER_OF_INVENTAR)
    private String numberOfInventar;

    @Column(name = COMMENT)
    private String comment;

    @Enumerated(value = EnumType.STRING)
    private EnumTypeFuel typeOfFuel;

    @Column(name = MILEAGE)
    private double mileage; // пробег

    @Column(name = DATE_OF_MILEAGE)
    private Date dateOfMileage;

    @Column(name = MASHIN_HOURS)
    private int mashineHours;

    @Column(name = NUMBER_OF_TAHOGRAF)
    private String numberOfTahograf;


    @Column(name = MODEL_TAHOGRAF)
    private String modelTahograf;


    @Column(name = DATE_POVERKA_OF_TAHOGRAF)
    private Date dateOfPoverkaTahograf;


    @Column(name = DATE_CALIBR_OF_TAHOGRAF)
    private Date dateCalibrOfTahograf;


    @Column(name = PLATON)
    private String platon;



    public GeneralData() {
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

    public EnumTypeFuel getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(EnumTypeFuel typeOfFuel) {
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

    public String getNumberOfTahograf() {
        return numberOfTahograf;
    }

    public void setNumberOfTahograf(String numberOfTahograf) {
        this.numberOfTahograf = numberOfTahograf;
    }

    public String getModelTahograf() {
        return modelTahograf;
    }

    public void setModelTahograf(String modelTahograf) {
        this.modelTahograf = modelTahograf;
    }

    public Date getDateOfPoverkaTahograf() {
        return dateOfPoverkaTahograf;
    }

    public void setDateOfPoverkaTahograf(Date dateOfPoverkaTahograf) {
        this.dateOfPoverkaTahograf = dateOfPoverkaTahograf;
    }

    public Date getDateCalibrOfTahograf() {
        return dateCalibrOfTahograf;
    }

    public void setDateCalibrOfTahograf(Date dateCalibrOfTahograf) {
        this.dateCalibrOfTahograf = dateCalibrOfTahograf;
    }

    public String getPlaton() {
        return platon;
    }

    public void setPlaton(String platon) {
        this.platon = platon;
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

package com.example.demo.entity.cars.car;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "passport_data")
public class PassportData {
    public static final String VIN = "vin";
    public static final String MODEL_TS = "modelTS";
    public static final String TYPE_TS = "typeTS";
    public static final String CATEGORY = "category";
    public static final String YEAR_OF_BUILD = "yearOfBuild";
    public static final String MODEL_OF_ENGINE = "modelOfEngine";
    public static final String ECCO_OF_ENGINE = "eccoClass";
    public static final String NUMBER_OF_ENGINE = "numberOfEngine";
    public static final String NUMBER_OF_CHASSIS = "numberOfChassis";
    public static final String NUMBER_OF_BODY = "numberOfBody";
    public static final String COLOR = "color";
    public static final String POWER_OF_ENGINE = "powerOfEngine";
    public static final String VOLUME_OF_ENGINE = "volumeOfEngine";
    public static final String MAX_MASS = "maxMass";
    public static final String MAX_MASS_WITHOUT = "maxMassWithout";
    public static final String BUILDER = "builder";
    public static final String NUMBER_OF_PASSPORT_TS = "numberOfPassportTS";
    public static final String DATE_OF_PASSPORT_TS = "dateOfPassportTS";
    public static final String PLACE_OF_INSSUANCE_OF_PASSPORT_TS = "placeOfIssuanceOfPassportTS";
    public static final String COST = "cost";
    public static final String REG_NUMBER = "regNumber";
    public static final String OLDREG_NUMBER = "oldregNumber";
    public static final String CERTIFICATE_OF_REGISTRATION = "certificateOfRegistration";
    public static final String PLACE_OF_REGISTRATION = "placeOfregistration";
    public static final String DATE_OF_REGISTRATION = "dateOfRegistration";
    public static final String TEMP_REGISTRATION = "tempRegistration";
    public static final String QUANTITY_OF_PALLET = "quantityOfPallet";
    public static final String LENGHT_OF_BODY = "lenghtOfBody";
    public static final String WIDHT_OF_BODY = "widhtOfBody";
    public static final String HEIGHT_OF_BODY = "heightOfBody";
    public static final String VOLUME_OF_BODY = "volumeOfBody";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "passportData", cascade = CascadeType.ALL)
    private Car car;

    @Column(name = VIN, unique = true)
    private String vin;

    @Column(name = MODEL_TS)
    private String modelTS;

    @Column(name = TYPE_TS)
    private String typeTS;

    @Column(name = CATEGORY)
    private String category;

    @Column(name = YEAR_OF_BUILD)
    private Date yearOfBuild;//дата выпуска

    @Column(name = MODEL_OF_ENGINE)
    private String modelOfEngine;

    @Column(name = ECCO_OF_ENGINE)
    private int eccoClass;

    @Column(name = NUMBER_OF_ENGINE)
    private String numberOfEngine;

    @Column(name = NUMBER_OF_CHASSIS)
    private String numberOfChassis; //номер шасси

    @Column(name = NUMBER_OF_BODY)
    private String numberOfBody; //номер кузова

    @Column(name = COLOR)
    private String color;

    @Column(name = POWER_OF_ENGINE)
    private int powerOfEngine; // в лошадинных силах

    @Column(name = VOLUME_OF_ENGINE)
    private int volumeOfEngine; //обьем двигателя

    @Column(name = MAX_MASS)
    private int maxMass;

    @Column(name = MAX_MASS_WITHOUT)
    private int maxMassWithout; //макс масса без нагрузки

    @Column(name = BUILDER)
    private String builder; // производитель

    @Column(name = NUMBER_OF_PASSPORT_TS)
    private String numberOfPassportTS;

    @Column(name = DATE_OF_PASSPORT_TS)
    private Date dateOfPassportTS;

    @Column(name = PLACE_OF_INSSUANCE_OF_PASSPORT_TS)
    private String placeOfIssuanceOfPassportTS;

//    @ManyToOne
//    @JoinColumn(name = "id_owner")
//    private Owner owner; перенесено в Car

    @Column(name = COST)
    private BigDecimal cost;
    // @Column(name = )
    //private Group group;

    @Column(name = REG_NUMBER)
    private String regNumber;

    @Column(name = OLDREG_NUMBER)
    private String oldregNumber;

    @Column(name = CERTIFICATE_OF_REGISTRATION)
    private String certificateOfRegistration;

    @Column(name = PLACE_OF_REGISTRATION)
    private String placeOfregistration;

    @Column(name = DATE_OF_REGISTRATION)
    private Date dateOfRegistration;

    @Column(name = TEMP_REGISTRATION)
    private Date tempRegistration;

    @Enumerated(value = EnumType.STRING)
    private EnumTypeOfBody typeOfBody; // сменить на енум

    @Column(name = QUANTITY_OF_PALLET)
    private int quantityOfPallet;

    @Column(name = LENGHT_OF_BODY)
    private double lenghtOfBody;

    @Column(name = WIDHT_OF_BODY)
    private double widhtOfBody;

    @Column(name = HEIGHT_OF_BODY)
    private double heightOfBody;

    @Column(name = VOLUME_OF_BODY)
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

    public int getPowerOfEngine() {
        return powerOfEngine;
    }

    public void setPowerOfEngine(int powerOfEngine) {
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
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

    public Date getDateTempRegistration() {
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

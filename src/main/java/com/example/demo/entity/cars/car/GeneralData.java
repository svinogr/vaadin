package com.example.demo.entity.cars.car;


import com.example.demo.entity.cars.FinanceResponsePerson;
import com.example.demo.entity.cars.owner.Contract;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class GeneralData {
    private Date dateOfTakeToBalanse;
    private boolean decommissioned; //списан
    private boolean fauly; //неисправный
    private String podrazdelenieOrGarage;
    private String colonna;
    private String numberOfGarage;
    private String numberOfInventar;
    private String comment;
    private String typeOfFuel;
    private double mileage; // пробег
    private Date dateOfMileage;
    private int mashineHours;
    private List<Trailer> trailers;

    private FinanceResponsePerson financeResponcePerson;
    private Contract contract;
    private BigInteger priceOfContract;

    private String license;
    private Date limiteOfLicense;

    private String numberOfTahograf;
    private String modelOfTahograf;
    private Date verificationLimit;
    private Date calibrationLimit;
    private String platon;


}

package com.example.demo.entity.jornal;

import com.example.demo.entity.cars.Driver;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.validators.BigDecimalValidator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "journal_item")
public class JournalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "car_id")
    private long car_id;
    @Enumerated(value = EnumType.STRING)
    private EnumTypeRecord enumTypeRecord;
    @Enumerated(value = EnumType.STRING)
    private EnumTypeTo typeTo; // для то
    @Enumerated(value = EnumType.STRING)
    private EnumTypeOil typeOil; // для смазки
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "kod")
    private String kod;

    @Column(name = "date_setup")
    private Date dateSetup;
    @Column(name = "setup_mileage")
    private double setupMileage;
    @Column(name = "cost")
    private BigDecimal cost;
    // private Driver buyer;
    //private Driver executor;
    //private Organisation organisation;
    @Column(name = "comment")
    private String comment;

    @Column(name = "date_delete")
    private Date datedelete;
    @Column(name = "delete_mileage")
    private double deleteMileage;
    @Column(name = "cost")
    private String cause;

}

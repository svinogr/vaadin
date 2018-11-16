package com.example.demo.entity.jornal;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "journal_item")
public class JournalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "changed")
    private String changed;

    private long id;
    @Column(name = "car_id")
    private long car_id;
    @Enumerated(value = EnumType.STRING)
    private EnumTypeRecord enumTypeRecord;
    @Enumerated(value = EnumType.STRING)
    private EnumTypeTO typeTo; // для то
    @Enumerated(value = EnumType.STRING)
    private EnumTypeOil typeOil; // для смазки
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "code")
    private String code;

    @Column(name = "date_setup")
    private Date dateSetup;
    @Column(name = "setup_mileage")
    private double setupMileage;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "type_of_units") // еденицы измерения
    private String typeOfUnits;

    // private Driver buyer;
    //private Driver executor;
    //private Organisation organisation;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date_delete")
    private Date datedelete;
    @Column(name = "delete_mileage")
    private double deleteMileage;
    @Column(name = "cause")
    private String cause;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public EnumTypeRecord getEnumTypeRecord() {
        return enumTypeRecord;
    }

    public void setEnumTypeRecord(EnumTypeRecord enumTypeRecord) {
        this.enumTypeRecord = enumTypeRecord;
    }

    public EnumTypeTO getTypeTo() {
        return typeTo;
    }

    public void setTypeTo(EnumTypeTO typeTo) {
        this.typeTo = typeTo;
    }

    public EnumTypeOil getTypeOil() {
        return typeOil;
    }

    public void setTypeOil(EnumTypeOil typeOil) {
        this.typeOil = typeOil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateSetup() {
        return dateSetup;
    }

    public void setDateSetup(Date dateSetup) {
        this.dateSetup = dateSetup;
    }

    public double getSetupMileage() {
        return setupMileage;
    }

    public void setSetupMileage(double setupMileage) {
        this.setupMileage = setupMileage;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDatedelete() {
        return datedelete;
    }

    public void setDatedelete(Date datedelete) {
        this.datedelete = datedelete;
    }

    public double getDeleteMileage() {
        return deleteMileage;
    }

    public void setDeleteMileage(double deleteMileage) {
        this.deleteMileage = deleteMileage;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTypeOfUnits() {
        return typeOfUnits;
    }

    public void setTypeOfUnits(String typeOfUnits) {
        this.typeOfUnits = typeOfUnits;
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

    @Override
    public String toString() {
        return "JournalItem{" +
                "car_id=" + car_id +
                ", enumTypeRecord=" + enumTypeRecord +
                ", typeTo=" + typeTo +
                ", typeOil=" + typeOil +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", dateSetup=" + dateSetup +
                ", setupMileage=" + setupMileage +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", typeOfUnits='" + typeOfUnits + '\'' +
                ", comment='" + comment + '\'' +
                ", datedelete=" + datedelete +
                ", deleteMileage=" + deleteMileage +
                ", cause='" + cause + '\'' +
                '}';
    }
}

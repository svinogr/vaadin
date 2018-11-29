package com.example.demo.entity.organisation;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//TODO сделать адрес классом общим для людей и организаций
    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "okpo")
    private String okpo;

    @Column(name = "inn")
    private String inn;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "egrul")
    private String egrul;

    @Column(name = "dateOfEgrul")
    private Date dateOfEgurl;

    public Organisation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getEgrul() {
        return egrul;
    }

    public void setEgrul(String egrul) {
        this.egrul = egrul;
    }

    public Date getDateOfEgurl() {
        return dateOfEgurl;
    }

    public void setDateOfEgurl(Date dateOfEgurl) {
        this.dateOfEgurl = dateOfEgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
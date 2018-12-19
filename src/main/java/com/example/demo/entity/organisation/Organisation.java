package com.example.demo.entity.organisation;

import com.example.demo.entity.Selectable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "organisation")
public class Organisation implements Selectable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//TODO сделать адрес классом общим для людей и организаций
    @Column(name = "address")
    private String address;

    @Column(name = "changed")
    private String changed;

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


    @Column(name = "kpp")
    private String kpp;

    public Organisation() {
    }

    public long getId() {
        return id;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
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

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
}

package org.example.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ansatt", schema = "innlevering_jpa")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ansatt_id;
    @Column(unique = true)
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private Date ansettelsedato;
    private String stilling;
    private BigDecimal maanedslonn;
    private int avdeling_id;


    @OneToMany(mappedBy = "ansatt") //betyr at den andre entiteten har ansatt osv definert
    private List<ProsjektDeltagelse> prosjektDeltagelse;

//    @OneToMany
//    @JoinColumn(name = "ansatt_id")
//    private List<ProsjektDeltagelse> prosjektDeltagelse;

    public Ansatt(){};
    public Ansatt(int ansatt_id, String brukernavn, String fornavn, String etternavn, Date ansettelsedato, String stilling, BigDecimal maanedslonn, int avdeling_id, List<ProsjektDeltagelse> prosjektDeltagelse) {
        this.ansatt_id = ansatt_id;
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsedato = ansettelsedato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
        this.avdeling_id = avdeling_id;
        this.prosjektDeltagelse = prosjektDeltagelse;
    }

    public Ansatt(int id, String brukernavn, String fornavn, String etternavn, Date ansettelsedato, String stilling, BigDecimal maanedslonn, int advedling_id){

    }

    public BigDecimal getMaanedslonn() {
        return maanedslonn;
    }

    public void setMaanedslonn(BigDecimal maanedslonn) {
        this.maanedslonn = maanedslonn;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public Date getAnsettelsedato() {
        return ansettelsedato;
    }

    public void setAnsettelsedato(Date ansettelsedato) {
        this.ansettelsedato = ansettelsedato;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    @Override
    public String toString() {
        return "Ansatt {" +
                "\n  Ansatt ID: " + ansatt_id +
                "\n  Brukernavn: " + brukernavn +
                "\n  Fornavn: " + fornavn +
                "\n  Etternavn: " + etternavn +
                "\n  Stilling: " + stilling +
                "\n  Månedslønn: " + maanedslonn +
                "\n  Ansettelsesdato: " + ansettelsedato +
                "\n  Avdeling ID: " + avdeling_id +
                "\n  Prosjektdeltagelser: " + (prosjektDeltagelse != null ? prosjektDeltagelse.size() : 0) +
                "\n}";
    }


    public Integer getAvdeling_id() {
        return avdeling_id;
    }

    public void setAvdeling_id(int avdeling_id) {
        this.avdeling_id = avdeling_id;
    }

    public int getAnsatt_id() {
        return ansatt_id;
    }

    public void setAnsatt_id(int ansatt_id) {
        this.ansatt_id = ansatt_id;
    }

    public List<ProsjektDeltagelse> getProsjektDeltagelse() {
        return prosjektDeltagelse;
    }

    public void setProsjektDeltagelse(List<ProsjektDeltagelse> prosjektDeltagelse) {
        this.prosjektDeltagelse = prosjektDeltagelse;
    }
}

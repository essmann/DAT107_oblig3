package org.example;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ansatt", schema = "innlevering_jpa")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private Date ansettelsedato;
    private String stilling;
    private BigDecimal maanedslonn;


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
        String str = "Brukernavn: %s Fornavn %s Etternavn %s Månedslønn %s Stilling %s Ansettelsesdato %s";
        return String.format(str, brukernavn, fornavn, etternavn, maanedslonn, stilling, ansettelsedato);
    }
}

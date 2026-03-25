package org.example.Entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProsjektDeltagelseId implements Serializable {

    private int ansatt;
    private int prosjekt;

    public ProsjektDeltagelseId() {}

    public ProsjektDeltagelseId(int ansatt, int prosjekt) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
    }

    public int getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(int ansatt) {
        this.ansatt = ansatt;
    }

    public int getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(int prosjekt) {
        this.prosjekt = prosjekt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProsjektDeltagelseId that = (ProsjektDeltagelseId) o;

        if (ansatt != that.ansatt) return false;
        return prosjekt == that.prosjekt;
    }

    @Override
    public int hashCode() {
        int result = ansatt;
        result = 31 * result + prosjekt;
        return result;
    }
}

package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prosjekt_medlemmer", schema = "innlevering_jpa")
public class ProsjektDeltagelse {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ProsjektDeltagelseId id;

    @ManyToOne
    @MapsId("ansatt")
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansatt;

    @ManyToOne
    @MapsId("prosjekt")
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    public int getAntall_timer() {
        return antall_timer;
    }

    public void setAntall_timer(int antall_timer) {
        this.antall_timer = antall_timer;
    }

    public ProsjektDeltagelseId getId() {
        return id;
    }

    public void setId(ProsjektDeltagelseId id) {
        this.id = id;
    }

    private int antall_timer = 0;

    public Ansatt getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(Ansatt ansatt) {
        this.ansatt = ansatt;
    }


    public Prosjekt getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }
    @Override
    public String toString() {
        return "ProsjektDeltagelse{" +
                "ansattId=" + (ansatt != null ? ansatt.getAnsatt_id() : null) +
                ", prosjektId=" + (prosjekt != null ? prosjekt.getProsjekt_id() : null) +
                ", timer=" + antall_timer +
                '}';
    }

}

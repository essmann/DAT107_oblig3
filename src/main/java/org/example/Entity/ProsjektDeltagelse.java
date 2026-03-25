package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prosjekt_medlemmer")
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

    private int timer = 0;

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
}

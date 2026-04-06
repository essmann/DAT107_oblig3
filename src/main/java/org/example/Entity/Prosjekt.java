package org.example.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prosjekt", schema = "innlevering_jpa")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjekt_id;
    private String navn;
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt")
    private List<ProsjektDeltagelse> deltagelse;

    public int getProsjekt_id() {
        return prosjekt_id;
    }

    public void setProsjekt_id(int prosjekt_id) {
        this.prosjekt_id = prosjekt_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}

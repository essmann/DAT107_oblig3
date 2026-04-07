package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avdeling", schema = "innlevering_jpa")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdeling_id;

    public int getAvdeling_id() {
        return avdeling_id;
    }

    private String navn;

    public String getNavn() {
        return navn;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @OneToOne
    @JoinColumn(name = "sjef_id")  // denne lager foreign key
    private Ansatt sjef;

    public Ansatt getSjef(){
        return this.sjef;
    }
    @Override
    public String toString() {
        return "Avdeling{" +
                "avdeling_id=" + avdeling_id +
                ", navn='" + navn + '\'' +
                ", sjef=" + (sjef != null ? sjef.getAnsatt_id() : "null") +
                '}';
    }

}

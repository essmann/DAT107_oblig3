package org.example.Entity;

import jakarta.persistence.*;

@Entity
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdeling_id;

    private String navn;

    @OneToOne
    @JoinColumn(name = "sjef_id")  // denne lager foreign key
    private Ansatt sjef;
}

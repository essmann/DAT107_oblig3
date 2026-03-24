package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prosjekt")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    private String beskrivelse;

    @ManyToMany
    private List<Ansatt> ansatte;



}

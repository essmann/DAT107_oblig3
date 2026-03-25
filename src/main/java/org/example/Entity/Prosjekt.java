package org.example.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prosjekt")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjekt_id;
    private String navn;
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt")
    private List<ProsjektDeltagelse> deltagelse;




}

package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.Entity.Ansatt;
import org.example.Entity.Prosjekt;
import org.example.Entity.ProsjektDeltagelse;

import java.util.List;

public class ProsjektDAO {

    public static ProsjektDeltagelse leggTilAnsattTilProsjekt(EntityManager em, int ansattNr, int prosjektNr) {
        Ansatt ansatt = em.find(Ansatt.class, ansattNr);
        if (ansatt == null) {
            throw new IllegalArgumentException("Ansatt med id: " + ansattNr + " ikke funnet.");
        }

        Prosjekt prosjekt = em.find(Prosjekt.class, prosjektNr);
        if (prosjekt == null) {
            throw new IllegalArgumentException("Prosjekt med id: " + prosjektNr + " ikke funnet.");
        }

        TypedQuery<ProsjektDeltagelse> query = em.createQuery(
                "SELECT p FROM ProsjektDeltagelse p WHERE p.ansatt = :ansatt AND p.prosjekt = :prosjekt",
                ProsjektDeltagelse.class
        );

        query.setParameter("ansatt", ansatt);
        query.setParameter("prosjekt", prosjekt);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        ProsjektDeltagelse result;

        try {
            result = query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            result = null;
        }

        if (result == null) {
            result = new ProsjektDeltagelse();
            result.setAnsatt(ansatt);
            result.setProsjekt(prosjekt);
            em.persist(result);
        }

        tx.commit();

        return result;
    }


}

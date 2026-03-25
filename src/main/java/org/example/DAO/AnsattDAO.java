package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.Entity.Ansatt;

import java.math.BigDecimal;
import java.util.Date;

public class AnsattDAO {
    private EntityManager em;

    public AnsattDAO(EntityManager em) {
        this.em = em;
    }

    public void leggTilAnsatt(Ansatt ansatt) {
        try{
            EntityTransaction tx = em.getTransaction();
            tx.begin(); // start transaction


            em.persist(ansatt);

            tx.commit(); // commit to DB

            em.close();
        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally{
            em.close();

        }
    }

    public static void insertDummyAnsatte(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Ansatt a1 = new Ansatt();
        a1.setBrukernavn("jdoe");
        a1.setFornavn("John");
        a1.setEtternavn("Doe");
        a1.setAnsettelsedato(new Date());
        a1.setStilling("Utvikler");
        a1.setMaanedslonn(new BigDecimal("50000"));
        a1.setAvdeling_id(1);

        Ansatt a2 = new Ansatt();
        a2.setBrukernavn("aith");
        a2.setFornavn("Anna");
        a2.setEtternavn("Smith");
        a2.setAnsettelsedato(new Date());
        a2.setStilling("Designer");
        a2.setMaanedslonn(new BigDecimal("48000"));
        a2.setAvdeling_id(2);

        Ansatt a3 = new Ansatt();
        a3.setBrukernavn("bsen");
        a3.setFornavn("Bjørn");
        a3.setEtternavn("Jensen");
        a3.setAnsettelsedato(new Date());
        a3.setStilling("Prosjektleder");
        a3.setMaanedslonn(new BigDecimal("60000"));
        a3.setAvdeling_id(1);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        tx.commit();
    }





}

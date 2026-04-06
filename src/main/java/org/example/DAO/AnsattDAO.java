package org.example.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.Entity.Ansatt;
import org.example.Entity.ProsjektDeltagelse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AnsattDAO {
    private EntityManager em;

    public AnsattDAO(EntityManager em) {
        this.em = em;
    }

    public boolean erSjef(int ansattNr) {
        try {
            Long count = em.createQuery(
                            "select count(p) from Avdeling p where p.sjef.ansatt_id = :ansattNr",
                            Long.class)
                    .setParameter("ansattNr", ansattNr)
                    .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void leggTilAnsatt(Ansatt ansatt) {
        EntityTransaction tx = em.getTransaction();

        try {
            if (ansatt.getAvdeling_id() == 0) {
                throw new IllegalArgumentException("Ansatt har ikke en avdeling id.");
            }

            tx.begin();
            em.persist(ansatt);
            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback(); // IMPORTANT
            e.printStackTrace();
        }
    }

    public static void insertDummyAnsatt(EntityManager em) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt a = new Ansatt();
            a.setBrukernavn("TEST");
            a.setFornavn("Ola");
            a.setEtternavn("Nordmann");
            a.setAnsettelsedato(new Date());
            a.setStilling("Utvikler");
            a.setMaanedslonn(new BigDecimal("50000"));
            a.setAvdeling_id(1); // make sure this exists in DB

            em.persist(a);

            tx.commit();
            System.out.println("Ansatt inserted!");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
    private static boolean harProsjektTimer(Ansatt ansatt){
        List<ProsjektDeltagelse> deltagelse = ansatt.getProsjektDeltagelse();

        for (ProsjektDeltagelse p : deltagelse) {
            int timer = p.getAntall_timer();
            if(timer>0){
                    return true;
            }
        }
        return false;
    }

    public static void oppdaterAnsatt(EntityManager em, Ansatt ansatt) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Hent eksisterende ansatt fra DB
            Ansatt eksisterende = em.find(Ansatt.class, ansatt.getAnsatt_id());
            if (eksisterende == null) {
                throw new IllegalArgumentException("Ansatt finnes ikke");
            }

            // Sjekk om ansatt er sjef i en avdeling
            Long sjefCount = em.createQuery(
                            "SELECT COUNT(a) FROM Avdeling a WHERE a.sjef.ansatt_id = :ansattId", Long.class)
                    .setParameter("ansattId", eksisterende.getAnsatt_id())
                    .getSingleResult();

            // Hvis prøver å endre avdeling og er sjef → kast feil
            if (sjefCount > 0 && eksisterende.getAvdeling_id() != ansatt.getAvdeling_id()) {
                throw new IllegalStateException("Kan ikke endre avdeling for en sjef.");
            }

            // Oppdater kun felt som er tillatt
            eksisterende.setFornavn(ansatt.getFornavn());
            eksisterende.setEtternavn(ansatt.getEtternavn());
            eksisterende.setBrukernavn(ansatt.getBrukernavn());
            eksisterende.setStilling(ansatt.getStilling());
            eksisterende.setMaanedslonn(ansatt.getMaanedslonn());
            eksisterende.setAnsettelsedato(ansatt.getAnsettelsedato());
            eksisterende.setAvdeling_id(ansatt.getAvdeling_id()); // trykket her er OK hvis ikke sjef

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }


    public static Ansatt finnAnsatt(EntityManager em, int ansattNr){
        try{
            Ansatt ansatt = em.find(Ansatt.class, ansattNr);
            return ansatt;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        }
        return null;
    }
    public static List<Ansatt> finnAlleAnsatte(EntityManager em){
        try{
            TypedQuery<Ansatt> query = em.createQuery( "SELECT a from Ansatt a", Ansatt.class);
            return query.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        }
        return null;
    }
    public static Ansatt finnAnsattFraBrukernavn(EntityManager em, String brukernavn){
        try{
            TypedQuery<Ansatt> query = em.createQuery(
                    "SELECT p FROM Ansatt p WHERE p.brukernavn = :ansatt",
                    Ansatt.class
            );

            query.setParameter("ansatt", brukernavn);
            Ansatt ansatt = query.getSingleResult();
            return ansatt;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        }
        return null;
    }

    public static Ansatt slettAnsatt(EntityManager em, int ansattNr){
            Ansatt ansatt = em.find(Ansatt.class, ansattNr);
            if(ansatt == null) throw new IllegalArgumentException("Ansatt med id: " + ansattNr + " ikke funnet");
            if(harProsjektTimer(ansatt)){
                throw new IllegalStateException("Kan ikke slette ansatt fordi den har timer jobbet i et prosjekt.");

            }
        try {
            em.getTransaction().begin();
            em.remove(ansatt); // <-- Dette sletter faktisk raden!
            em.getTransaction().commit();
            return ansatt;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Ruller tilbake hvis noe krasjer i DB
            }
            throw e; // Kaster feilen videre så vi vet at DB-slettingen feilet
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

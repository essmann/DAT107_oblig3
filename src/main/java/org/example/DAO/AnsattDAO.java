package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.Entity.Ansatt;
import org.example.Entity.ProsjektDeltagelse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AnsattDAO {

    // legg til ansatt
    public static void leggTilAnsatt(EntityManager em, Ansatt ansatt) {
        EntityTransaction tx = em.getTransaction();

        try {
            if (ansatt.getAvdeling_id() == 0) {
                throw new IllegalArgumentException("ansatt må ha avdeling_id.");
            }

            tx.begin();
            em.persist(ansatt);
            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // oppdater ansatt
    public static void oppdaterAnsatt(EntityManager em, Ansatt ansatt) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt eksisterende = em.find(Ansatt.class, ansatt.getAnsatt_id());
            if (eksisterende == null) {
                throw new IllegalArgumentException("ansatt finnes ikke");
            }

            // sjekk om ansatt er sjef
            Long sjefCount = em.createQuery(
                            "SELECT COUNT(a) FROM Avdeling a WHERE a.sjef.ansatt_id = :id",
                            Long.class
                    ).setParameter("id", ansatt.getAnsatt_id())
                    .getSingleResult();

            // hvis prøver å bytte avdeling og er sjef → stopp
            if (sjefCount > 0 &&
                    eksisterende.getAvdeling_id() != ansatt.getAvdeling_id()) {
                throw new IllegalStateException("kan ikke endre avdeling for en sjef.");
            }

            eksisterende.setFornavn(ansatt.getFornavn());
            eksisterende.setEtternavn(ansatt.getEtternavn());
            eksisterende.setBrukernavn(ansatt.getBrukernavn());
            eksisterende.setStilling(ansatt.getStilling());
            eksisterende.setMaanedslonn(ansatt.getMaanedslonn());
            eksisterende.setAnsettelsedato(ansatt.getAnsettelsedato());
            eksisterende.setAvdeling_id(ansatt.getAvdeling_id());

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    // finn ansatt på id
    public static Ansatt finnAnsatt(EntityManager em, int id) {
        return em.find(Ansatt.class, id);
    }

    // finn alle ansatte
    public static List<Ansatt> finnAlleAnsatte(EntityManager em) {
        TypedQuery<Ansatt> query =
                em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
        return query.getResultList();
    }

    // finn ansatt på brukernavn
    public static Ansatt finnAnsattFraBrukernavn(EntityManager em, String brukernavn) {
        try {
            return em.createQuery(
                            "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn",
                            Ansatt.class
                    ).setParameter("brukernavn", brukernavn)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    // slett ansatt
    public static void slettAnsatt(EntityManager em, int id) {
        EntityTransaction tx = em.getTransaction();

        try {
            Ansatt ansatt = em.find(Ansatt.class, id);
            if (ansatt == null) {
                throw new IllegalArgumentException("ansatt finnes ikke");
            }

            // sjekk om ansatt har prosjekt-timer
            if (harProsjektTimer(ansatt)) {
                throw new IllegalStateException("ansatt har prosjekt-timer.");
            }

            tx.begin();
            em.remove(ansatt);
            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }
    // sett ny månedslønn på ansatt
    public static void settLonn(EntityManager em, int ansattId, BigDecimal nyLonn) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if (ansatt == null) {
                throw new IllegalArgumentException("ansatt finnes ikke");
            }

            ansatt.setMaanedslonn(nyLonn);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // sjekk om ansatt har timer registrert
    private static boolean harProsjektTimer(Ansatt ansatt) {
        for (ProsjektDeltagelse p : ansatt.getProsjektDeltagelse()) {
            if (p.getAntall_timer() > 0) return true;
        }
        return false;
    }

    // dummy insert for testing
    public static Ansatt insertDummyAnsatt(EntityManager em) {
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
            a.setAvdeling_id(1);

            em.persist(a);

            tx.commit();
            return a;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return null;
        }
    }
}

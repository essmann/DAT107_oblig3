package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Entity.Ansatt;
import org.example.Entity.Avdeling;

import java.util.List;

public class AvdelingDAO {
    public static void leggTilAvdeling(EntityManager em, String navn, Ansatt sjef ) {
        
    }
    public static Avdeling finnAvdelingMedId(EntityManager em, int id){
            try{
                Avdeling avdeling = em.find( Avdeling.class, id);
                return avdeling;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }

    }
    public static void printAnsatteWithHighlightedBoss(EntityManager em, int avdeling_id) {
        try {
            Avdeling avdeling = finnAvdelingMedId(em, avdeling_id);
            if (avdeling == null) {
                System.out.println("Avdeling ikke funnet.");
                return;
            }

            List<Ansatt> ansatte = finnAlleAnsatte(em, avdeling_id);
            if (ansatte == null || ansatte.isEmpty()) {
                System.out.println("Ingen ansatte funnet i avdelingen.");
                return;
            }

            int sjefId = avdeling.getSjef().getAnsatt_id();

            System.out.println("=== Avdeling: " + avdeling.getNavn() + " ===");
            for (Ansatt ansatt : ansatte) {
                if (ansatt.getAnsatt_id() == sjefId) {
                    System.out.println("👑 [SJEF] " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
                } else {
                    System.out.println("   " + ansatt.getFornavn() + " " + ansatt.getEtternavn() + " - " + ansatt.getStilling());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Ansatt> finnAlleAnsatte(EntityManager em, int avdeling_id){
        try{
            TypedQuery<Ansatt> query = em.createQuery("select p from Ansatt p where p.avdeling_id = :avdeling_id", Ansatt.class);
            query.setParameter("avdeling_id", avdeling_id);

            return query.getResultList();
        }
        catch(Exception e){

            e.printStackTrace();
            return null;

        }
    }
}

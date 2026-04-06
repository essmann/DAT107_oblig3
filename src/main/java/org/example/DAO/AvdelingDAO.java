package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Entity.Ansatt;
import org.example.Entity.Avdeling;

import java.util.List;

public class AvdelingDAO {
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

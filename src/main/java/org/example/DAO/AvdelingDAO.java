package org.example.DAO;

import jakarta.persistence.EntityManager;
import org.example.Entity.Avdeling;

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
}

package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAO.AnsattDAO;
import org.example.Entity.Ansatt;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Oblig3");


        //Lag entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        AnsattDAO ansattDAO = new AnsattDAO(entityManager);

        AnsattDAO.insertDummyAnsatte(entityManager);
        getAlleAnsatte(entityManager);

    }

    private static void getAlleAnsatte(EntityManager entityManager) {
        List<Ansatt> ansatte = entityManager.createQuery("SELECT p from Ansatt p", Ansatt.class).getResultList();
        for(Ansatt ans : ansatte){
        System.out.println(ans.toString());

        }
    }

    private static void leggTilAnsattTilProsjekt(Ansatt ansatt, int prosjektNr){

    }
}

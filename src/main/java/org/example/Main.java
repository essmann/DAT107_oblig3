package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAO.AnsattDAO;
import org.example.DAO.AvdelingDAO;
import org.example.DAO.ProsjektDAO;
import org.example.Entity.Ansatt;
import org.example.Entity.Avdeling;
import org.example.Entity.ProsjektDeltagelse;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Oblig3");


        //Lag entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        AnsattDAO ansattDAO = new AnsattDAO(entityManager);

//        AnsattDAO.insertDummyAnsatte(entityManager);
//        getAlleAnsatte(entityManager);

        List<Ansatt> liste = AnsattDAO.finnAlleAnsatte(entityManager);
        System.out.println("Fetching all ansatte..");
        for(Ansatt ansatt : liste){
            System.out.println(ansatt);
        }
        var ansatt = AnsattDAO.finnAnsatt(entityManager, 1);

        System.out.println(ansatt);
//
//        ProsjektDeltagelse deltagelse = ProsjektDAO.leggTilAnsattTilProsjekt(entityManager, 1, 1);
//
//        System.out.println(deltagelse);

        Ansatt ansatt2 = AnsattDAO.finnAnsattFraBrukernavn(entityManager, "CD34");
        System.out.println(ansatt2);

        Avdeling avdeling = AvdelingDAO.finnAvdelingMedId(entityManager, 2);
        System.out.println(avdeling);
        System.out.println(avdeling.getSjef());

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

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


        //1) Finner alle ansatte !!!!
        List<Ansatt> liste = AnsattDAO.finnAlleAnsatte(entityManager);
        System.out.println("Finner alle ansatte");
        for (Ansatt ansatt : liste) {
            System.out.println(ansatt);
        }

        //2) Printer alle ansatte i en avdeling !!!!

        AvdelingDAO.printAnsatteWithHighlightedBoss(entityManager, 2);



        //3) Oppdatere hvilken avdeling en ansatt jobber på.

        Ansatt ansatt = AnsattDAO.insertDummyAnsatt(entityManager);
        System.out.println("Ansatt vi akkurat la til:");
        System.out.println(ansatt);

        ansatt.setFornavn("Ken William");
        ansatt.setEtternavn("Austrheim");
        ansatt.setAvdeling_id(3);

        AnsattDAO.oppdaterAnsatt(entityManager, ansatt);
        System.out.println("Oppdaterte ansatt:");

        System.out.println(AnsattDAO.finnAnsatt(entityManager, ansatt.getAnsatt_id()));

        //4) Lage en ny avdeling og tildele den en ansatt.


//        ProsjektDeltagelse deltagelse = ProsjektDAO.leggTilAnsattTilProsjekt(entityManager, 1, 1);
//
//        System.out.println(deltagelse);

//        Ansatt testS = AnsattDAO.finnAnsatt(entityManager, 5);
//         testS.setAvdeling_id(4);
//        AnsattDAO.oppdaterAnsatt(entityManager, testS);
//        Ansatt ansatt2 = AnsattDAO.finnAnsattFraBrukernavn(entityManager, "CD34");
//        System.out.println(ansatt2);
//
//        Avdeling avdeling = AvdelingDAO.finnAvdelingMedId(entityManager, 2);
//        System.out.println(avdeling);
//        System.out.println(avdeling.getSjef());

//        AnsattDAO.insertDummyAnsatt(entityManager);

//        System.out.println("Finner alle ansatte i avdelingen...");
//        List<Ansatt> ansatte = AvdelingDAO.finnAlleAnsatte(entityManager, 1);
//        for(Ansatt ans : ansatte ){
//            System.out.println(ans);
//        }


    }


    private static void getAlleAnsatte(EntityManager entityManager) {
        List<Ansatt> ansatte = entityManager.createQuery("SELECT p from Ansatt p", Ansatt.class).getResultList();
        for (Ansatt ans : ansatte) {
            System.out.println(ans.toString());

        }
    }

    private static void leggTilAnsattTilProsjekt(Ansatt ansatt, int prosjektNr) {

    }


}

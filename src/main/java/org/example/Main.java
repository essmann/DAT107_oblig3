package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Oblig3");


        //Lag entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();


    }

    private static void getAlleAnsatte(EntityManager entityManager) {

    }
}

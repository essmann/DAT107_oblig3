package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.DAO.AnsattDAO;
import org.example.DAO.AvdelingDAO;
import org.example.Entity.Ansatt;
import org.example.Entity.Avdeling;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Oblig3");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENY ===");
            System.out.println("1. Søk ansatt på ID");
            System.out.println("2. Søk ansatt på brukernavn");
            System.out.println("3. List alle ansatte");
            System.out.println("4. Oppdater ansatt");
            System.out.println("5. Legg til ny ansatt");
            System.out.println("6. List alle avdelinger");
            System.out.println("7. Søk avdeling på ID");
            System.out.println("8. Vis ansatte i avdeling");
            System.out.println("9. Bytt avdeling for ansatt");
            System.out.println("10. Legg til ny avdeling");

            System.out.println("0. Avslutt");
            System.out.print("Velg: ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // flush

            switch (valg) {
                case 1 -> {
                    System.out.print("Ansatt ID: ");
                    int id = scanner.nextInt();
                    Ansatt a = AnsattDAO.finnAnsatt(em, id);
                    System.out.println(a);
                }

                case 2 -> {
                    System.out.print("Brukernavn: ");
                    String brukernavn = scanner.nextLine();
                    Ansatt a = AnsattDAO.finnAnsattFraBrukernavn(em, brukernavn);
                    System.out.println(a);
                }

                case 3 -> {
                    List<Ansatt> liste = AnsattDAO.finnAlleAnsatte(em);
                    for (Ansatt a : liste) {
                        System.out.println(a);
                    }
                }

                case 4 -> {
                    System.out.print("Ansatt ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Ansatt a = AnsattDAO.finnAnsatt(em, id);
                    if (a == null) {
                        System.out.println("Fant ikke ansatt.");
                        break;
                    }

                    System.out.print("Ny stilling: ");
                    String stilling = scanner.nextLine();

                    System.out.print("Ny lønn: ");
                    var lønn = scanner.nextBigDecimal();

                    a.setStilling(stilling);
                    a.setMaanedslonn(lønn);

                    AnsattDAO.oppdaterAnsatt(em, a);
                    System.out.println("Oppdatert!");
                }

                case 5 -> {
                    System.out.print("Fornavn: ");
                    String fornavn = scanner.nextLine();

                    System.out.print("Etternavn: ");
                    String etternavn = scanner.nextLine();

                    System.out.print("Brukernavn: ");
                    String brukernavn = scanner.nextLine();

                    System.out.print("Stilling: ");
                    String stilling = scanner.nextLine();

                    System.out.print("Lønn: ");
                    var lønn = scanner.nextBigDecimal();

                    // vis avdelinger først
                    List<Avdeling> avdelinger = AvdelingDAO.finnAlleAvdelinger(em);
                    System.out.println("Velg avdeling:");
                    for (Avdeling avd : avdelinger) {
                        System.out.println(avd.getAvdeling_id() + ": " + avd.getNavn());
                    }

                    System.out.print("Avdeling ID: ");
                    int avdelingId = scanner.nextInt();
                    scanner.nextLine();

                    Ansatt ny = new Ansatt();
                    ny.setFornavn(fornavn);
                    ny.setEtternavn(etternavn);
                    ny.setBrukernavn(brukernavn);
                    ny.setStilling(stilling);
                    ny.setMaanedslonn(lønn);
                    ny.setAvdeling_id(avdelingId);

                    AnsattDAO.leggTilAnsatt(em, ny);
                    System.out.println("Ny ansatt lagt til!");
                }

                case 6 -> {
                    List<Avdeling> avdelinger = AvdelingDAO.finnAlleAvdelinger(em);

                    for (Avdeling avd : avdelinger) {
                        System.out.println("ID: " + avd.getAvdeling_id() +
                                " | Navn: " + avd.getNavn());
                    }
                }
                case 7 -> {
                    System.out.print("Avdeling ID: ");
                    int id = scanner.nextInt();

                    Avdeling avd = AvdelingDAO.finnAvdelingMedId(em, id);
                    System.out.println(avd);
                }

                case 8 -> {
                    System.out.print("Avdeling ID: ");
                    int id = scanner.nextInt();

                    AvdelingDAO.printAnsatteWithHighlightedBoss(em, id);
                }

                case 9 -> {
                    System.out.print("Ansatt ID: ");
                    int ansattId = scanner.nextInt();

                    Ansatt a = AnsattDAO.finnAnsatt(em, ansattId);
                    if (a == null) {
                        System.out.println("Fant ikke ansatt");
                        break;
                    }

                    // sjekk om ansatt er sjef
                    Long sjefCount = em.createQuery(
                            "SELECT COUNT(a) FROM Avdeling a WHERE a.sjef.ansatt_id = :id",
                            Long.class
                    ).setParameter("id", ansattId).getSingleResult();

                    if (sjefCount > 0) {
                        System.out.println("Kan ikke bytte avdeling – ansatt er sjef!");
                        break;
                    }

                    // vis avdelinger
                    List<Avdeling> avdelinger = AvdelingDAO.finnAlleAvdelinger(em);
                    for (Avdeling avd : avdelinger) {
                        System.out.println(avd.getAvdeling_id() + ": " + avd.getNavn());
                    }

                    System.out.print("Ny avdeling ID: ");
                    int nyAvd = scanner.nextInt();

                    a.setAvdeling_id(nyAvd);
                    AnsattDAO.oppdaterAnsatt(em, a);

                    System.out.println("Oppdatert!");
                }

                case 10 -> {
                    scanner.nextLine();

                    System.out.print("Navn på avdeling: ");
                    String navn = scanner.nextLine();

                    // vis ansatte
                    List<Ansatt> ansatte = AnsattDAO.finnAlleAnsatte(em);
                    for (Ansatt ans : ansatte) {
                        System.out.println(ans.getAnsatt_id() + ": " + ans.getFornavn());
                    }

                    System.out.print("Velg sjef (ansatt ID): ");
                    int sjefId = scanner.nextInt();

                    Ansatt sjef = AnsattDAO.finnAnsatt(em, sjefId);
                    if (sjef == null) {
                        System.out.println("Ugyldig ansatt.");
                        break;
                    }

                    AvdelingDAO.leggTilAvdeling(em, navn, sjef);

                    System.out.println("Ny avdeling opprettet!");
                }

                case 0 -> running = false;

                default -> System.out.println("Ugyldig valg.");
            }
        }

        em.close();
        emf.close();
    }
}

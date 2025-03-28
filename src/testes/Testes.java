/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entities.*;
import java.sql.Date;
import java.util.List;
import services.*;

/**
 *
 * @author hamza
 */
public class Testes {

    public static void main(String[] args) {

//        //Test creation
        CompetitionService cs = new CompetitionService();
//        System.out.println("");
//        Competition comp1 = new Competition("Football", Date.valueOf("2025-03-17"), "ENS Marrakech", "Collectif");
//        Competition comp2 = new Competition("Saut en longueur", Date.valueOf("2025-03-18"), "ENS Marrakech", "individuel");
//        Competition comp3 = new Competition("Volley-ball", Date.valueOf("2025-03-20"), "ENS Marrakech", "Collectif");
//        Competition comp4 = new Competition("Basket-ball", Date.valueOf("2025-04-07"), "ENS Marrakech", "Collectif");
//        cs.create(comp1);
//        cs.create(comp2);
//        cs.create(comp3);
//        cs.create(comp4);
//
//        //Test restriction of duplication 
//        Competition comp5 = new Competition("Saut en longueur", Date.valueOf("2025-03-18"), "ENS Marrakech", "individuel");
//        Competition comp6 = new Competition("Volley-ball", Date.valueOf("2025-03-20"), "ENS Marrakech", "Collectif");
//        cs.create(comp5);
//        cs.create(comp6);
//        System.out.println("");

//        //Find by id
//        Competition foundComp = cs.findById(3);
//        if (foundComp != null) {
//            System.out.println("\nCompétition found : " + foundComp.getId() + ", " + foundComp.getNom() + ", " + foundComp.getDate() + ", " + foundComp.getType());
//        } else {
//            System.err.println("\nNothing found!");
//        }
//
//        //Update
//        foundComp.setNom("Gymnastique");
//        foundComp.setType("Individuel");
//        foundComp.setDate(Date.valueOf("2025-03-25"));
//        cs.update(foundComp);
//
//        //Delete
//        foundComp = cs.findById(5);
//        cs.delete(foundComp);
//        System.out.println("\nCompetition deleted successfully!");
        //FindAll
//        List<Competition> competitions = cs.findAll();
//        for (Competition c : competitions) {
//            System.out.println("Id : " + c.getId() + ", Nom : " + c.getNom() + ", Date : " + c.getDate() + ", Lieu : " + c.getLieu() + ", type : " + c.getType());
//        }
//        System.out.println("");
//
        //----------------------------------------------------------------------------------------------------------------------------------------------------------
        EtudiantService es = new EtudiantService();
        // Create
//        Etudiant e1 = new Etudiant("RAMI", "Ayman", "rami.ayman@example.com");
//        Etudiant e2 = new Etudiant("ALAMI", "Imad", "alami.imad@example.com");
//        Etudiant e3 = new Etudiant("DROUBI", "Ali", "droubi.ali@example.com");
//        Etudiant e4 = new Etudiant("RAMI", "Ayman", "rami.ayman@example.com");
//        Etudiant e5 = new Etudiant("ALAMI", "Imad", "alami.imad@example.com");
//        Etudiant e6 = new Etudiant("DROUBI", "Ali", "droubi.ali@example.com");
//        Etudiant e7 = new Etudiant("SAIDI", "Fatima", "saidi.fatima@example.com");
//        Etudiant e8 = new Etudiant("BENNOUNA", "Karim", "bennouna.karim@example.com");
//        Etudiant e9 = new Etudiant("EL FILALI", "Leila", "elfilali.leila@example.com");
//        Etudiant e10 = new Etudiant("ZAOUI", "Youssef", "zaoui.youssef@example.com");
//        es.create(e1);
//        es.create(e2);
//        es.create(e3);
//        es.create(e4);
//        es.create(e5);
//        es.create(e6);
//        es.create(e7);
//        es.create(e8);
//        es.create(e9);
//        es.create(e10);
//
//        //Test restriction of duplication 
//        es.create(e4);
//        es.create(e5);

//        // Find an existing student by ID
//        Etudiant e = es.findById(9);
//        if (e != null) {
//            System.out.println("\n Student found (Id : " + e.getId() + ", " + e.getNom() + " " + e.getPrenom() + ", " + e.getEmail()+")");
//        } else {
//            System.err.println("\n No student found with the specified ID.");
//        }
//
//        // Update
//        if (e != null) {
//            // Update the student's email
//            e.setEmail("droubi@example.com");
//            es.update(e);
//            System.out.println("\n Student updated successfully  (Id : " + e.getId() + ", " + e.getNom() + " " + e.getPrenom() + ", " + e.getEmail()+")");
//        } else {
//            System.err.println("\n No student found with the specified ID.");
//        }
//
//        // Delete
//        e = es.findById(2);
//        if (e != null) {
//            es.delete(e);
//        } else {
//            System.err.println("\n No student found with the specified ID.");
//        }
//        List<Etudiant> etudiants = es.findAll();
//        System.out.println("");
//        for (Etudiant et : etudiants) {
//            System.out.println("Id : " + et.getId() + " - " + et.getNom() + " " + et.getPrenom() + " - " + et.getEmail());
//        }
//        System.out.println("");
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        InscriptionService is = new InscriptionService();

        //create
        Inscription inscr1 = new Inscription(cs.findById(7), es.findById(102));
        Inscription inscr2 = new Inscription(cs.findById(1), es.findById(140));
        Inscription inscr3 = new Inscription(cs.findById(2), es.findById(147));
        Inscription inscr4 = new Inscription(cs.findById(1), es.findById(148));
        Inscription inscr5 = new Inscription(cs.findById(7), es.findById(140));
        Inscription inscr6 = new Inscription(cs.findById(8), es.findById(102));

        is.create(inscr1);
        is.create(inscr2);
        is.create(inscr3);
        is.create(inscr4);
        is.create(inscr5);
        is.create(inscr6);
//
//        //Test restriction of duplication
//        is.create(inscr3);
//        is.create(inscr4);
//
        // Update inscription (competition)
        Inscription x = is.findByCompetitionNameAndEtudiantId("Football", 148);
        int oldCompId = x.getCompetition().getId();
        int etudId = x.getEtudiant().getId();
        int newCompId = cs.findById(13).getId();
        is.updateCompetition(oldCompId, etudId, newCompId);
////        
////        // Delete
////        is.delete(inscr4);
//        System.out.println("");
//        List<Inscription> inscriptions = is.findAll();
//        for (Inscription i : inscriptions) {
//            Etudiant etudiant = i.getEtudiant();
//            Competition competition = i.getCompetition();
//            System.out.println(etudiant.getNom() + " " + etudiant.getPrenom() + " - " + competition.getNom());
//        }
//        System.out.println("");
        //-----------------------------------------------------------------------------------------------------------
        AdminService as = new AdminService();

//        Admin a1 = new Admin("azerty", "321");
//        Admin a2 = new Admin("qwerty", "123");
//
//        as.create(a1);
//        as.create(a2);
//
////        //Test restriction of duplication
////        as.create(a1);
////        as.create(a2);
//        
//        List<Admin> admins = as.findAll();
//        for (Admin a : admins) {
//            System.out.println("Login : " + a.getLogin() + " - Password : " + a.getPassword());
//        }
    }
}

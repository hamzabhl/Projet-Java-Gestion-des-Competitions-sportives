/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entities.Competition;
import entities.Etudiant;
import java.sql.Date;
import java.util.List;
import services.CompetitionService;
import services.EtudiantService;
import services.InscriptionCompetitionService;
import entities.InscriptionCompetition;

/**
 *
 * @author hamza
 */
public class Testes {

    public static void main(String[] args) {

        //Test creation
        CompetitionService cs = new CompetitionService();
        Competition comp1 = new Competition("Football", Date.valueOf("2025-03-17"), "ENS Marrakech", "Collectif");
        Competition comp2 = new Competition("Saut en longueur", Date.valueOf("2025-03-18"), "ENS Marrakech", "individuel");
        Competition comp3 = new Competition("Volley-ball", Date.valueOf("2025-03-20"), "ENS Marrakech", "Collectif");
        Competition comp4 = new Competition("Basket-ball", Date.valueOf("2025-04-07"), "ENS Marrakech", "Collectif");

        cs.create(comp1);
        cs.create(comp2);
        cs.create(comp3);
        cs.create(comp4);

        //Test restriction of duplication 
        Competition comp5 = new Competition("Saut en longueur", Date.valueOf("2025-03-18"), "ENS Marrakech", "individuel");
        Competition comp6 = new Competition("Volley-ball", Date.valueOf("2025-03-20"), "ENS Marrakech", "Collectif");
        Competition comp7 = new Competition("Hand-ball", Date.valueOf("2025-04-12"), "ENS Marrakech", "Collectif");

        cs.create(comp5);
        cs.create(comp6);
        cs.create(comp7);

        //Find by id
        Competition foundComp = cs.findById(3);
        if (foundComp != null) {
            System.out.println("\nCompétition found : " + foundComp.getId() + ", " + foundComp.getNom() + ", " + foundComp.getDate() + ", " + foundComp.getType());
        } else {
            System.err.println("\nNothing found!");
        }

        //Update
        foundComp.setNom("Gymnastique");
        foundComp.setType("Individuel");
        foundComp.setDate(Date.valueOf("2025-03-25"));
        cs.update(foundComp);
        System.out.println("Competition updated successfully : " + foundComp.getNom() + ", " + foundComp.getDate() + ", " + foundComp.getType());

        //Delete
        foundComp = cs.findById(5);
        cs.delete(foundComp);
        System.out.println("\nCompetition deleted successfully!");

        //FindAll
        System.out.println("");
        List<Competition> competitions = cs.findAll();
        for (Competition c : competitions) {
            System.out.println("Id : " + c.getId() + ", Nom : " + c.getNom() + ", Date : " + c.getDate() + ", Lieu : " + c.getLieu() + ", type : " + c.getType());
        }

        //----------------------------------------------------------------------------------------------------------------------------------------------------------
        EtudiantService es = new EtudiantService();
        // Create
        Etudiant e1 = new Etudiant("RAMI", "Ayman", "rami.ayman@example.com");
        Etudiant e2 = new Etudiant("ALAMI", "Imad", "alami.imad@example.com");
        Etudiant e3 = new Etudiant("DROUBI", "Ali", "droubi.ali@example.com");
        es.create(e1);
        es.create(e2);
        es.create(e3);

        //Test restriction of duplication 
        Etudiant e4 = new Etudiant("ALAMI", "Imad", "alami.imad@example.com");
        Etudiant e5 = new Etudiant("DROUBI", "Ali", "droubi.ali@example.com");
        es.create(e4);
        es.create(e5);

        // Find an existing student by ID
        Etudiant e = es.findById(9);
        if (e != null) {
            System.out.println("\n Student found (Id : " + e.getId() + ", " + e.getNom() + " " + e.getPrenom() + ", " + e.getEmail()+")");
        } else {
            System.err.println("\n No student found with the specified ID.");
        }

        // Update
        if (e != null) {
            // Update the student's email
            e.setEmail("droubi@example.com");
            es.update(e);
            System.out.println("\n Student updated successfully  (Id : " + e.getId() + ", " + e.getNom() + " " + e.getPrenom() + ", " + e.getEmail()+")");
        } else {
            System.err.println("\n No student found with the specified ID.");
        }

        // Delete
        e = es.findById(2);
        if (e != null) {
            es.delete(e);
            System.out.println("\n \n" +
"            System.out.println(\"Id : \"+ et.getId() + \" - \" + et.getNom() + \" \" + et.getPrenom() + \" - \" + et.getEmail());Student ( "+e.getNom()+ " " + e.getPrenom() +") Deleted successfully!");
        } else {
            System.err.println("\n No student found with the specified ID.");
        }

        // Retrieve all students
        List<Etudiant> etudiants = es.findAll();
        for (Etudiant et : etudiants) 
            System.out.println("Id : "+ et.getId() + " - " + et.getNom() + " " + et.getPrenom() + " - " + et.getEmail());

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        InscriptionCompetitionService ics = new InscriptionCompetitionService();
        InscriptionCompetition inscr1 = new InscriptionCompetition(cs.findById(1).getId(), es.findById(1).getId());
        InscriptionCompetition inscr2 = new InscriptionCompetition(cs.findById(2).getId(), es.findById(3).getId());
        ics.create(inscr1);
        ics.create(inscr2);           
        
        //Test restriction of duplication
        InscriptionCompetition inscr3 = new InscriptionCompetition(cs.findById(8).getId(), es.findById(1).getId());
        InscriptionCompetition inscr4 = new InscriptionCompetition(cs.findById(2).getId(), es.findById(3).getId());
        ics.create(inscr3);
        ics.create(inscr4);

        // Find an existing inscription by ID
        InscriptionCompetition inscr = ics.findById(2);
        if (inscr != null) {
            System.out.println("\n Inscription found (Id : " + inscr.getId()
                    + ", Competition : " + cs.findById(inscr.getCompetitionId()).getNom()
                    + ", Etudiant : " + es.findById(inscr.getEtudiantId()).getNom() +" " + es.findById(inscr.getEtudiantId()).getPrenom() + ")");
        } else {
            System.err.println("\n No inscription found with the specified ID.");
        }

        // Update
        if (inscr != null) {
            inscr.setCompetitionId(7);
            ics.update(inscr);
            System.out.println("\n Inscription updated successfully  (Etudiant : " + es.findById(ics.findById(2).getEtudiantId()).getNom() + " " + es.findById(ics.findById(2).getEtudiantId()).getPrenom() + ", Competition : " + cs.findById(7).getNom() + ")");
        } else {
            System.err.println("\n No Inscription found with the specified ID.");
        }

        // Delete
        if (inscr != null) {
            // Update the student's email
            inscr = ics.findById(3);
            ics.delete(inscr);
        } else {
            System.err.println("\n No Inscription found with the specified ID.");
        }

        // Retrieve all students
        List<InscriptionCompetition> inscriptions = ics.findAll();
        for (InscriptionCompetition i : inscriptions) {
            System.out.println("Id Inscription: " + i.getId() + " " + es.findById(i.getEtudiantId()).getNom() + " " + es.findById(i.getEtudiantId()).getPrenom() + " " + cs.findById(i.getEtudiantId()).getNom());
        }

    }
}

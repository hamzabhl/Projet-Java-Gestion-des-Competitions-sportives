/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entities.Competition;
import java.sql.Date;
import java.util.List;
import services.CompetitionService;

/**
 *
 * @author hamza
 */
public class CompetitionTestes {

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
//        
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

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entities.Competition;
import java.sql.Date;
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
       
        //Show list
        cs.printAllCompetitions();

    }
}

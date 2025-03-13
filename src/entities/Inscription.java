/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hamza
 */
public class Inscription {
    
    private int id;
    private Competition competition;
    private Etudiant etudiant;

    public Inscription(int id, Competition competition, Etudiant etudiant) {
        this.id = id;
        this.competition = competition;
        this.etudiant = etudiant;
    }
    
    public Inscription(Competition competition, Etudiant etudiant) {
        this.competition = competition;
        this.etudiant = etudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

}
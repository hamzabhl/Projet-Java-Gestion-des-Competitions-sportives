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
public class InscriptionCompetition {
    private int id;
    private int competitionId;
    private int etudiantId;

    // Constructor
    public InscriptionCompetition(int id, int competitionId, int etudiantId) {
        this.id = id;
        this.competitionId = competitionId;
        this.etudiantId = etudiantId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    @Override
    public String toString() {
        return "InscriptionCompetition{" +
                "id=" + id +
                ", competitionId=" + competitionId +
                ", etudiantId=" + etudiantId +
                '}';
    }
}

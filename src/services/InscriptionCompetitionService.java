/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author hamza
 */

import connexion.Connexion;
import entities.InscriptionCompetition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InscriptionCompetitionService {
    private Connection connection;

    public InscriptionCompetitionService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    public void createInscription(InscriptionCompetition inscription) {
        String query = "INSERT INTO inscription_competition (competition_id, etudiant_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, inscription.getCompetitionId());
            statement.setInt(2, inscription.getEtudiantId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inscription.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Inscription créée avec succès. ID : " + inscription.getId());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'inscription : " + e.getMessage());
        }
    }
}
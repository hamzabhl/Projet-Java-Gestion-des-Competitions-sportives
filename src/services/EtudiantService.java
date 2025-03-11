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
import entities.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EtudiantService {
    private Connection connection;

    public EtudiantService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    public void createEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO etudiant (nom, prenom, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getEmail());
            statement.executeUpdate();
            System.out.println("Étudiant Ajouté avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }

    public void updateEtudiant(Etudiant etudiant) {
        String query = "UPDATE etudiant SET nom = ?, prenom = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getEmail());
            statement.setInt(4, etudiant.getId());
            statement.executeUpdate();
            System.out.println("Étudiant mis à jour avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
        }
    }

    public void deleteEtudiant(int id) {
        String query = "DELETE FROM etudiant WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Étudiant supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
        }
    }
}

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
import dao.IDao;
import entities.InscriptionCompetition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscriptionCompetitionService implements IDao<InscriptionCompetition> {

    private final Connection connection;

    public InscriptionCompetitionService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public boolean create(InscriptionCompetition inscription) {
        String checkQuery = "SELECT COUNT(*) FROM inscriptioncompetition WHERE competition_id = ? AND etudiant_id = ?";
        String insertQuery = "INSERT INTO inscriptioncompetition (competition_id, etudiant_id) VALUES (?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Check if the inscription already exists
            checkStatement.setInt(1, inscription.getCompetitionId());
            checkStatement.setInt(2, inscription.getEtudiantId());

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("L'inscription existe déjà dans la base de données.");
                return false;
            }

            // Insert the new inscription
            insertStatement.setInt(1, inscription.getCompetitionId());
            insertStatement.setInt(2, inscription.getEtudiantId());

            insertStatement.executeUpdate();
            System.out.println("Inscription créée avec succès.");
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'inscription : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(InscriptionCompetition inscription) {
        String query = "UPDATE inscriptioncompetition SET competition_id = ?, etudiant_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, inscription.getCompetitionId());
            statement.setInt(2, inscription.getEtudiantId());
            statement.setInt(3, inscription.getId());
            statement.executeUpdate();
            System.out.println("Inscription mise à jour avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'inscription : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(InscriptionCompetition inscription) {
        String query = "DELETE FROM inscriptioncompetition WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, inscription.getId());
            statement.executeUpdate();
            System.out.println("Inscription supprimée avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'inscription : " + e.getMessage());
            return false;
        }
    }

    @Override
    public InscriptionCompetition findById(int id) {
        String query = "SELECT * FROM inscriptioncompetition WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new InscriptionCompetition(
                        resultSet.getInt("id"),
                        resultSet.getInt("competition_id"),
                        resultSet.getInt("etudiant_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'inscription : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<InscriptionCompetition> findAll() {
        List<InscriptionCompetition> inscriptions = new ArrayList<>();
        String query = "SELECT * FROM inscriptioncompetition";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                InscriptionCompetition inscription = new InscriptionCompetition(
                        resultSet.getInt("id"),
                        resultSet.getInt("competition_id"),
                        resultSet.getInt("etudiant_id")
                );
                inscriptions.add(inscription);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des inscriptions : " + e.getMessage());
        }
        return inscriptions;
    }
}
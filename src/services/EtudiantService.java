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
import entities.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService implements IDao<Etudiant> {

    private final Connection connection;

    public EtudiantService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public boolean create(Etudiant etudiant) {
        String checkQuery = "SELECT COUNT(*) FROM etudiant WHERE nom = ? AND prenom = ? AND email = ?";
        String insertQuery = "INSERT INTO etudiant (nom, prenom, email) VALUES (?, ?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Check if the student already exists
            checkStatement.setString(1, etudiant.getNom());
            checkStatement.setString(2, etudiant.getPrenom());
            checkStatement.setString(3, etudiant.getEmail());

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("L'étudiant existe déjà dans la base de données.");
                return false;
            }

            // Insert the new student
            insertStatement.setString(1, etudiant.getNom());
            insertStatement.setString(2, etudiant.getPrenom());
            insertStatement.setString(3, etudiant.getEmail());

            insertStatement.executeUpdate();
            System.out.println("Étudiant créé avec succès : " + etudiant.getNom() + " " + etudiant.getPrenom());
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'étudiant : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Etudiant etudiant) {
        String query = "UPDATE etudiant SET nom = ?, prenom = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getEmail());
            statement.setInt(4, etudiant.getId());
            statement.executeUpdate();
            System.out.println("Étudiant mis à jour avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Etudiant etudiant) {
        String query = "DELETE FROM etudiant WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, etudiant.getId());
            statement.executeUpdate();
            System.out.println("Étudiant supprimé avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Etudiant findById(int id) {
        String query = "SELECT * FROM etudiant WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Etudiant(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'étudiant : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiant";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email")
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des étudiants : " + e.getMessage());
        }
        return etudiants;
    }
}

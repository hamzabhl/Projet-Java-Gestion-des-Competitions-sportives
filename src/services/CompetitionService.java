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
import entities.Competition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionService implements IDao<Competition> {

    private final Connection connection;

    public CompetitionService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public boolean create(Competition competition) {
        String checkQuery = "SELECT COUNT(*) FROM competition WHERE nom = ? AND date = ? AND lieu = ? AND type = ?";
        String insertQuery = "INSERT INTO competition (nom, date, lieu, type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            checkStatement.setString(1, competition.getNom());
            checkStatement.setDate(2, competition.getDate());
            checkStatement.setString(3, competition.getLieu());
            checkStatement.setString(4, competition.getType());

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("La compétition existe déjà dans la base de données.");
                return false;
            }

            insertStatement.setString(1, competition.getNom());
            insertStatement.setDate(2, competition.getDate());
            insertStatement.setString(3, competition.getLieu());
            insertStatement.setString(4, competition.getType());

            insertStatement.executeUpdate();
            System.out.println("Competition created successfully : " + competition.getNom());
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la compétition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Competition competition) {
        String query = "UPDATE competition SET nom = ?, date = ?, lieu = ?, type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, competition.getNom());
            statement.setDate(2, competition.getDate());
            statement.setString(3, competition.getLieu());
            statement.setString(4, competition.getType());
            statement.setInt(5, competition.getId());
            statement.executeUpdate();
            System.out.println("Compétition mise à jour avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la compétition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Competition competition) {
        String query = "DELETE FROM competition WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, competition.getId());
            statement.executeUpdate();
            System.out.println("Compétition supprimée avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la compétition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Competition findById(int id) {
        String query = "SELECT * FROM competition WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Competition(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDate("date"),
                        resultSet.getString("lieu"),
                        resultSet.getString("type")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la compétition : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Competition> findAll() {
        List<Competition> competitions = new ArrayList<>();
        String query = "SELECT * FROM competition";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Competition competition = new Competition(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDate("date"),
                        resultSet.getString("lieu"),
                        resultSet.getString("type")
                );
                competitions.add(competition);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des compétitions : " + e.getMessage());
        }
        return competitions;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 9f2884e6694b183b82b91df91e935b9ce26fe497

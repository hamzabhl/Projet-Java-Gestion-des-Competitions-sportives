package services;

import connexion.Connexion;
import dao.IDao;
import entities.Inscription;
import entities.Competition;
import entities.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscriptionService implements IDao<Inscription> {

    private final Connection connection;

    public InscriptionService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public boolean create(Inscription i) {
        String checkQuery = "SELECT COUNT(*) FROM inscription WHERE competition_id = ? AND etudiant_id = ?";
        String insertQuery = "INSERT INTO inscription (competition_id, etudiant_id) VALUES (?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            checkStatement.setInt(1, i.getCompetition().getId());
            checkStatement.setInt(2, i.getEtudiant().getId());

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("L'inscription existe déjà.");
                return false;
            }

            insertStatement.setInt(1, i.getCompetition().getId());
            insertStatement.setInt(2, i.getEtudiant().getId());

            insertStatement.executeUpdate();
            System.out.println("Inscription créée avec succès!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'inscription : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Inscription i) {
        String checkQuery = "SELECT COUNT(*) FROM inscription WHERE competition_id = ? AND etudiant_id = ?";
        String updateQuery = "UPDATE inscription SET competition_id = ?, etudiant_id = ? WHERE competition_id = ? AND etudiant_id = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setInt(1, i.getCompetition().getId());
            checkStatement.setInt(2, i.getEtudiant().getId());

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("L'inscription existe déjà.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification des doublons : " + e.getMessage());
            return false;
        }

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, i.getCompetition().getId());
            statement.setInt(2, i.getEtudiant().getId());
            statement.setInt(3, i.getCompetition().getId());
            statement.setInt(4, i.getEtudiant().getId());
            statement.executeUpdate();
            System.out.println("Inscription mise à jour avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'inscription : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Inscription i) {
        String query = "DELETE FROM inscription WHERE competition_id = ? AND etudiant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, i.getCompetition().getId());
            statement.setInt(2, i.getEtudiant().getId());
            statement.executeUpdate();
            System.out.println("Inscription supprimée avec succès.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'inscription : " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Inscription findById(int i){
        return null;
    } 

    @Override
    public List<Inscription> findAll() {
        List<Inscription> inscriptions = new ArrayList<>();
        String query = "SELECT * FROM inscription";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Competition competition = new CompetitionService().findById(resultSet.getInt("competition_id"));
                Etudiant etudiant = new EtudiantService().findById(resultSet.getInt("etudiant_id"));
                inscriptions.add(new Inscription(competition, etudiant));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des inscriptions : " + e.getMessage());
        }
        return inscriptions;
    }
}

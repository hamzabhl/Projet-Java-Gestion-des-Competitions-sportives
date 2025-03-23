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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean updateCompetition(int oldCompetitionId, int etudiantId, int newCompetitionId) {
        String checkQuery = "SELECT COUNT(*) FROM inscription WHERE competition_id = ? AND etudiant_id = ?";
        String updateQuery = "UPDATE inscription SET competition_id = ? WHERE competition_id = ? AND etudiant_id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setInt(1, newCompetitionId);
            checkStatement.setInt(2, etudiantId);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.err.println("L'inscription existe déjà pour cette compétition.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification des doublons : " + e.getMessage());
            return false;
        }
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, newCompetitionId);
            statement.setInt(2, oldCompetitionId);
            statement.setInt(3, etudiantId);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Compétition mise à jour avec succès.");
                return true;
            } else {
                System.out.println("Aucune mise à jour effectuée.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la compétition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Inscription i) {
        return false;
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
    public Inscription findById(int i) {
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

    public Inscription findByCompetitionNameAndEtudiantId(String competitionName, int etudiantId) {
        String sql = "SELECT i.*, c.id AS comp_id, c.nom AS comp_nom, c.date, c.lieu, c.type, "
                + "e.nom AS etu_nom, e.prenom, e.email "
                + "FROM inscription i "
                + "JOIN competition c ON i.competition_id = c.id "
                + "JOIN etudiant e ON i.etudiant_id = e.id "
                + "WHERE c.nom = ? AND e.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, competitionName);
            stmt.setInt(2, etudiantId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Competition comp = new Competition(
                        rs.getInt("comp_id"),
                        rs.getString("comp_nom"),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        rs.getString("type")
                );
                Etudiant etudiant = new Etudiant(
                        etudiantId,
                        rs.getString("etu_nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                return new Inscription(comp, etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Integer> getStudentCountByCompetition() {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT c.nom, COUNT(i.etudiant_id) AS student_count "
                + "FROM competition c "
                + "JOIN inscription i ON c.id = i.competition_id "
                + "GROUP BY c.nom";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String competitionName = rs.getString("nom");
                int studentCount = rs.getInt("student_count");
                data.put(competitionName, studentCount);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des données : " + e.getMessage());
        }
        return data;
    }

    public Map<String, Integer> getStudentCountByCompetitionType() {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT c.type, COUNT(i.etudiant_id) AS student_count "
                + "FROM competition c "
                + "JOIN inscription i ON c.id = i.competition_id "
                + "GROUP BY c.type";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String type = rs.getString("type");
                int count = rs.getInt("student_count");
                data.put(type, count);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des données : " + e.getMessage());
        }
        return data;
    }

}

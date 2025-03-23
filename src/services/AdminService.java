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
import entities.Admin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements IDao<Admin> {

    private final Connection connection;

    public AdminService() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public boolean create(Admin admin) {
        String query = "INSERT INTO Admin (login, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.executeUpdate();
            System.out.println("Admin created successfully: " + admin.getLogin());
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating admin: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Admin admin) {
        String query = "UPDATE Admin SET password = ? WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getPassword());
            statement.setString(2, admin.getLogin());
            statement.executeUpdate();
            System.out.println("Admin updated successfully: " + admin.getLogin());
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating admin: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Admin admin) {
        String query = "DELETE FROM Admin WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getLogin());
            statement.executeUpdate();
            System.out.println("Admin deleted successfully: " + admin.getLogin());
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting admin: " + e.getMessage());
            return false;
        }
    }

    public Admin findByLogin(String login) {
        String query = "SELECT * FROM Admin WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Admin(
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding admin: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Admin findById(int id) {
        return null;
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving admins: " + e.getMessage());
        }
        return admins;
    }

    public static String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

//    public static void sendEmail(String code, String toEmail) throws Exception {
//        final String fromEmail = "your_email@gmail.com"; // Your Gmail
//        final String password = "your_app_password";     // App Password (not your login password)
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        });
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(fromEmail));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//        message.setSubject("Your Verification Code");
//        message.setText("Your verification code is: " + code);
//
//        Transport.send(message);
//    }

}

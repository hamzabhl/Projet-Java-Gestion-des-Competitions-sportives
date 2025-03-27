/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package connexion;
/**
 *
 * @author hamza
 */
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//public class Connexion {
//
//    private static Connexion instance;
//    private Connection connection;
//
//    private static final String URL = "jdbc:mysql://localhost:3306/gcompsport";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
//    
//
//
//    
//    private Connexion() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connexion à la base de données établie.");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Échec de la connexion à la base de données : " + e.getMessage());
//        }
//    }
//
//    public static Connexion getInstance() {
//        if (instance == null) {
//            instance = new Connexion();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return this.connection;
//    }
//
//    public void closeConnection() {
//        if (this.connection != null) {
//            try {
//                this.connection.close();
//                System.out.println("Connexion à la base de données fermée.");
//            } catch (SQLException e) {
//                System.err.println("Échec de la fermeture de la connexion : " + e.getMessage());
//            }
//        }
//    }
//}
package connexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Connexion {

    private static Connexion instance;
    private Connection connection;

    private Connexion() {
        InputStream input = null;

        try {
            // Try to load from external file
            File externalFile = new File("db.properties");
            if (externalFile.exists()) {
                input = new FileInputStream(externalFile);
            } else {
                input = getClass().getClassLoader().getResourceAsStream("connexion/db.properties");
            }

            if (input == null) {
                throw new FileNotFoundException("db.properties not found!");
            }

            Properties p = new Properties();
            p.load(input);

            String driver = p.getProperty("jdbc.driver");
            String url = p.getProperty("jdbc.url");
            String username = p.getProperty("jdbc.username");
            String password = p.getProperty("jdbc.password");

            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion à la base de données établie.");

        } catch (Exception e) {
            e.printStackTrace(); // Always print stack trace
            JOptionPane.showMessageDialog(null, "Erreur de connexion : " + e.getMessage());
        }
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.err.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

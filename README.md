# Gestion des Compétitions Sportives
## Contexte général
La gestion des compétitions sportives implique un suivi rigoureux des participants, des inscriptions, du calendrier des épreuves, et de la gestion administrative associée. Traditionnellement, ces tâches sont effectuées manuellement ou avec des outils génériques peu adaptés, entraînant ainsi des erreurs, pertes de temps, et difficultés dans le suivi en temps réel.
Le projet présenté ici concerne la réalisation d'une application informatique, développée en Java Swing avec une base de données MySQL reliée via JDBC, permettant d'automatiser et faciliter l'organisation, le suivi et la gestion des compétitions sportives.  

## ❗ Problématique
• Difficulté de gestion administrative liée à un nombre élevé d'inscriptions et de participants.  
• Risques d'erreurs humaines fréquents dans le traitement manuel des données.  
• Complexité dans la planification et le suivi des compétitions en temps réel.  
• Manque d'un système efficace pour le stockage sécurisé et la consultation rapide des informations (statistiques, inscriptions).

## Objectifs
• Concevoir et développer une application ergonomique avec une interface graphique intuitive (Java Swing) facilitant l'utilisation.  
• Informatiser la gestion des inscriptions.  
• Réduire drastiquement les erreurs de saisie et de traitement des données.  
• Proposer une gestion efficace et sécurisée des données avec une connexion stable à une base de données (MySQL via JDBC et phpMyAdmin).

## Functionalities
• Gérer une compétition (CRUD).  
• Inscrire un étudiant (Créer et Inscrire, Modifier l'inscription, Modifier les informations, Supprimer l'étudiant et son Inscription).  
• Filtrer les inscriptions par compétition.  
• Consulter les statistiques (Taux d'Etudiants en chaque compétition, Taux d'Etudiants en chaque type de compétition).

## Technologies utilisées :
1. Interface Graphique (GUI) :Java Swing  
 • Création d’interfaces utilisateur intuitives, interactives et conviviales.  
 • Utilisation de composants graphiques (JFrame, JTable, JButton, JTextField, etc.).

2. Langage de programmation : Java  
 • Langage orienté objet robuste, portable et sécurisé.  
 • Gestion simplifiée des connexions avec les bases de données grâce à JDBC.

3. Accès aux données : JDBC (Java Database Connectivity)  
 • Connexion à la base de données MySQL.  
 • Exécution de requêtes SQL directement depuis Java.

4. Système de gestion de bases de données (SGBD) : MySQL  
 • Stockage des données structurées (entités : Admin, Étudiant, Compétition, Inscription).  

5. Interface d'administration de la base de données : phpMyAdmin  
 • Gestion visuelle et simplifiée de la base de données MySQL.  

6. Architecture logicielle : Architecture en couches  
 • Séparation claire entre GUI, Services métier, DAO, Entités et Base de données.  
 • Modularité facilitant la maintenance et l'évolution.

7. Environnement de développement : Netbeans IDE 8.0.2  

8. Gestion de projet et de versions : Git  
 • Suivi des versions, gestion collaborative du code source.


## Class Diagram
<img width="507" alt="Image" src="https://github.com/user-attachments/assets/5a61eeb6-7921-47ed-9ebb-a95cb621e201" />

## Use Case Diagram
<img width="647" alt="Image" src="https://github.com/user-attachments/assets/45750b97-ff35-4b47-bb73-9afbee3d0a74" />

## DataBase
### Schéma relationnel 
 • Compétition (id, nom, date, lieu, type)  
 • Étudiant (id, nom, prénom, email)  
 • Inscription (competition_id, etudiant_id)

### SQL tables 

```sql
-- Table: Compétition
CREATE TABLE Competition (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL
);

-- Table: Étudiant
CREATE TABLE Etudiant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prénom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Table: Inscription
CREATE TABLE Inscription (
    competition_id INT NOT NULL,
    etudiant_id INT NOT NULL,
    PRIMARY KEY(competition_id, etudiant_id),
    FOREIGN KEY (competition_id) REFERENCES Competition(id),
    FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id),
    UNIQUE (competition_id, etudiant_id) -- Dans une compétition, l'étudiant doit être inscrit une seule fois
);
```
# Démonstration Vidéo

https://github.com/user-attachments/assets/952a816b-d624-446a-aa74-24d33facf639

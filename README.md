# Gestion des Compétitions Sportives
Ce projet permet de gérer les compétitions sportives au sein d'une école ou université.
## Functionalities
• Créer une compétition.  
• Inscrire un étudiant.  
• Filtrer les inscriptions par type de compétition.  
• Rechercher un étudiant inscrit.
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

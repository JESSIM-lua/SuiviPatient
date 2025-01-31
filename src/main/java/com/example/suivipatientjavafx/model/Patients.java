package com.example.suivipatientjavafx.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Clé primaire auto-incrémentée

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "email", unique = true, length = 150)
    private String email;

    @Column(name = "date_enregistrement", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement;

    // Constructeurs

    public Patients() {
        // Initialisation de la date d'enregistrement à la date actuelle
        this.dateEnregistrement = new Date();
    }

    public Patients(String nom, String prenom, Date dateNaissance, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.email = email;
        this.dateEnregistrement = new Date(); // Date d'enregistrement automatique
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    // Méthode `toString()` pour affichage des informations du patient
    @Override
    public String toString() {
        return "Patient [ID=" + id +
                ", Nom=" + nom +
                ", Prénom=" + prenom +
                ", Date de naissance=" + dateNaissance +
                ", Téléphone=" + (telephone != null ? telephone : "Non renseigné") +
                ", Email=" + (email != null ? email : "Non renseigné") +
                ", Date d'enregistrement=" + dateEnregistrement + "]";
    }
}

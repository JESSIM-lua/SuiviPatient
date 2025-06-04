package com.example.suivipatientjavafx.model;


import com.example.suivipatientjavafx.Controller.RendezVousController;

import javax.persistence.*;

@Entity
@Table(name = "Rendezvous")
public class Rendezvous extends RendezVousController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_id", nullable = false, length = 100)
    private int patient_id;

    @Column(name = "utilisateur_id", nullable = false, length = 100)
    private int utilisateur_id;

    @Column(name = "date_rendezvous", nullable = false)
    private String date_rendezvous;

    @Column(name = "motif", nullable = false, length = 254)
    private String motif;

    @Column(name = "etat", nullable = false, length = 100)
    private String etat;

    @Column(name = "date_creation")
    private String date_creation;

    // Constructors
    public Rendezvous() {
    }

    public Rendezvous(int patient_id, int utilisateur_id, String date_rendezvous, String motif, String etat, String date_creation) {
        this.patient_id = patient_id;
        this.utilisateur_id = utilisateur_id;
        this.date_rendezvous = date_rendezvous;
        this.motif = motif;
        this.etat = etat;
        this.date_creation = date_creation;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getDate_rendezvous() {
        return date_rendezvous;
    }

    public void setDate_rendezvous(String date_rendezvous) {
        this.date_rendezvous = date_rendezvous;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "Rendezvous [id=" + id + ", patient_id=" + patient_id + ", utilisateur_id=" + utilisateur_id + ", date_rendezvous=" + date_rendezvous + ", motif=" + motif + ", etat=" + etat + ", date_creation=" + date_creation + "]";
    }


}

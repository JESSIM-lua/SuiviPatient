package com.example.suivipatientjavafx.model;

import javax.persistence.*;

@Entity
@Table(name = "Traitements")
public class Traitements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_id", nullable = false)
    private int patient_id;

    @Column(name = "medicament_id", nullable = false)
    private int medicament_id;


    @Column(name = "duree_jours", nullable = false)
    private int duree_jours;

    @Column(name = "dosage", nullable = false, length = 100)
    private String dosage;

    @Column(name = "frequence", nullable = false, length = 100)
    private String frequence;

    @Column(name = "date_debut", nullable = false)
    private String date_debut;

    @Column(name = "date_fin", nullable = false)
    private String date_fin;

    @Column(name = "recommandations", nullable = false, length = 100)
    private String recommandations;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private Patients patient;
    @ManyToOne
    @JoinColumn(name = "medicament_id", insertable = false, updatable = false)
    private Medicament medicament;




    // Constructors
    public Traitements() {
    }

    public Traitements(int id, int duree_jours, String dosage, String frequence, String date_debut, String date_fin, String recommandations, Patients patient, Medicament medicament) {
        this.id = id;
        this.duree_jours = duree_jours;
        this.dosage = dosage;
        this.frequence = frequence;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.recommandations = recommandations;
        this.patient = patient;
        this.medicament = medicament;
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

    public int getMedicament_id() {
        return medicament_id;
    }

    public void setMedicament_id(int medicament_id) {
        this.medicament_id = medicament_id;
    }

    public int getDuree_jours() {
        return duree_jours;
    }

    public void setDuree_jours(int duree_jours) {
        this.duree_jours = duree_jours;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    @Override
    public String toString() {
        return "Traitements [id=" + id + ", patient_id=" + patient_id + ", medicament_id=" + medicament_id + ", duree_jours=" + duree_jours + ", dosage=" + dosage + ", frequence=" + frequence + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", recommandations=" + recommandations + "]";
    }







}

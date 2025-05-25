package com.example.suivipatientjavafx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "substance_active", length = 100)
    private String substanceActive;

    @Column(name = "dose_maximale_journalière")
    private float doseMaxJournaliere;

    @Column(name = "dose_mortelle")
    private float doseMortelle;

    @Column(name = "populations_cibles", columnDefinition = "TEXT")
    private String populationsCibles;

    @Column(name = "populations_contre_indiquees", columnDefinition = "TEXT")
    private String populationsContreIndiquees;

    @Column(name = "frequence_maximale", length = 50)
    private String frequenceMaximale;

    @Column(name = "duree_maximale_traitement")
    private int dureeMaximaleTraitement;

    @Column(name = "effets_secondaires", columnDefinition = "TEXT")
    private String effetsSecondaires;

    @Column(name = "interactions", columnDefinition = "TEXT")
    private String interactions;

    @Column(name = "forme", length = 50)
    private String forme;

    @Column(name = "stock_disponible")
    private int stockDisponible;

    @Column(name = "date_expiration")
    private Date dateExpiration;

    @Column(name = "prix_unitaire")
    private float prixUnitaire;

    @Column(name = "fournisseur", length = 100)
    private String fournisseur;



    //Methodes
    public void ajouterStock(int quantite) {
        this.stockDisponible += quantite;
    }

    public void retirerStock(int quantite) {
        this.stockDisponible -= quantite;
    }

    public boolean verifierStock(int quantite) {

        if (this.stockDisponible >= quantite) {
            return true;
        }
        return false;
    }

    public boolean verifierDateExpiration() {
        Date date = new Date();
        if (date.after(this.dateExpiration)) {
            return false;
        }
        return true;
    }

    public boolean verifierDoseMaximaleJournaliere(float dose) {
        if (dose <= this.doseMaxJournaliere) {
            return true;
        }
        return false;
    }

    public boolean verifierDoseMortelle(float dose) {
        if (dose <= this.doseMortelle) {
            return true;
        }
        return false;

    }

    public boolean verifierDureeTraitement(int duree) {
        if (duree <= this.dureeMaximaleTraitement) {
            return true;
        }
        return false;
    }

    public boolean verifierFrequenceMaximale(String frequence) {
        if (frequence.equals(this.frequenceMaximale)) {
            return true;
        }
        return false;
    }

    public boolean verifierPopulationsCibles(String population) {
        if (this.populationsCibles.contains(population)) {
            return true;
        }
        return false;

    }

    public boolean verifierPopulationsContreIndiquees(String population) {
        if (this.populationsContreIndiquees.contains(population)) {
            return true;
        }
        return false;}

    public boolean verifierEffetsSecondaires(String effets) {
        if (this.effetsSecondaires.contains(effets)) {
            return true;
        }
        return false;
    }

    // Override
    @Override
    public String toString() {
        return "Medicament [id=" + id + ", nom=" + nom + ", substanceActive=" + substanceActive
                + ", doseMaxJournaliere=" + doseMaxJournaliere + ", doseMortelle=" + doseMortelle
                + ", populationsCibles=" + populationsCibles + ", populationsContreIndiquees="
                + populationsContreIndiquees + ", frequenceMaximale=" + frequenceMaximale + ", dureeMaximaleTraitement="
                + dureeMaximaleTraitement + ", effetsSecondaires=" + effetsSecondaires + ", interactions="
                + interactions + ", forme=" + forme + ", stockDisponible=" + stockDisponible + ", dateExpiration="
                + dateExpiration + ", prixUnitaire=" + prixUnitaire + ", fournisseur=" + fournisseur + "]";
    }

    // Constructeurs
    public Medicament() {
    }

    public Medicament(int id, String nom, String substanceActive, float doseMaxJournaliere, float doseMortelle,
                      String populationsCibles, String populationsContreIndiquees, String frequenceMaximale,
                      int dureeMaximaleTraitement, String effetsSecondaires, String interactions, String forme,
                      int stockDisponible, Date dateExpiration, float prixUnitaire, String fournisseur) {
        this.id = id;
        this.nom = nom;
        this.substanceActive = substanceActive;
        this.doseMaxJournaliere = doseMaxJournaliere;
        this.doseMortelle = doseMortelle;
        this.populationsCibles = populationsCibles;
        this.populationsContreIndiquees = populationsContreIndiquees;
        this.frequenceMaximale = frequenceMaximale;
        this.dureeMaximaleTraitement = dureeMaximaleTraitement;
        this.effetsSecondaires = effetsSecondaires;
        this.interactions = interactions;
        this.forme = forme;
        this.stockDisponible = stockDisponible;
        this.dateExpiration = dateExpiration;
        this.prixUnitaire = prixUnitaire;
        this.fournisseur = fournisseur;
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

    public String getSubstanceActive() {
        return substanceActive;
    }

    public void setSubstanceActive(String substanceActive) {
        this.substanceActive = substanceActive;
    }

    public float getDoseMaxJournaliere() {
        return doseMaxJournaliere;
    }

    public void setDoseMaxJournaliere(float doseMaxJournaliere) {
        this.doseMaxJournaliere = doseMaxJournaliere;
    }

    public float getDoseMortelle() {
        return doseMortelle;
    }

    public void setDoseMortelle(float doseMortelle) {
        this.doseMortelle = doseMortelle;
    }

    public String getPopulationsCibles() {
        return populationsCibles;
    }

    public void setPopulationsCibles(String populationsCibles) {
        this.populationsCibles = populationsCibles;
    }

    public String getPopulationsContreIndiquees() {
        return populationsContreIndiquees;
    }

    public void setPopulationsContreIndiquees(String populationsContreIndiquees) {
        this.populationsContreIndiquees = populationsContreIndiquees;
    }

    public String getFrequenceMaximale() {
        return frequenceMaximale;
    }

    public void setFrequenceMaximale(String frequenceMaximale) {
        this.frequenceMaximale = frequenceMaximale;
    }

    public int getDureeMaximaleTraitement() {
        return dureeMaximaleTraitement;
    }

    public void setDureeMaximaleTraitement(int dureeMaximaleTraitement) {
        this.dureeMaximaleTraitement = dureeMaximaleTraitement;
    }

    public String getEffetsSecondaires() {
        return effetsSecondaires;
    }

    public void setEffetsSecondaires(String effetsSecondaires) {
        this.effetsSecondaires = effetsSecondaires;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }


    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }


    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}

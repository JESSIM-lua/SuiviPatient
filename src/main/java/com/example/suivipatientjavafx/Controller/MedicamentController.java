package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.MedicamentDAO;
import com.example.suivipatientjavafx.model.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// Use the JavaFX TextField here
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MedicamentController implements Initializable {
    @FXML
    private TextField medicamentFeld;  // JavaFX TextField

    @FXML
    private Label affiche;

    @FXML
    private ComboBox<String> comb;

    private final MedicamentDAO medicamentDAO = new MedicamentDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        comboBox.setItems(FXCollections.observableArrayList("A", "B", "C"));
        ObservableList<String> items = FXCollections.observableArrayList("Id", "Dose", "Population", "Complete");

        comb.setItems(items);

    }

    @FXML
    public void btnMedicament(ActionEvent actionEvent) {
        String medicamentName = medicamentFeld.getText();

        if (medicamentName.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs !");
            return;
        }

        Medicament foundMedicament = medicamentDAO.getMedicamentByName(medicamentName);

        String nom = foundMedicament.getNom();
        int id = foundMedicament.getId();
        String substanceActive = foundMedicament.getSubstanceActive();
        float doseMaxJournaliere = foundMedicament.getDoseMaxJournaliere();
        float doseMortelle = foundMedicament.getDoseMortelle();
        String populationsCibles = foundMedicament.getPopulationsCibles();
        String populationsContreIndiquees = foundMedicament.getPopulationsContreIndiquees();
        String frequenceMaximale = foundMedicament.getFrequenceMaximale();
        int dureeMaximaleTraitement = foundMedicament.getDureeMaximaleTraitement();
        String effetsSecondaires = foundMedicament.getEffetsSecondaires();
        String interactions = foundMedicament.getInteractions();
        String forme = foundMedicament.getForme();
        int stockDisponible = foundMedicament.getStockDisponible();
        String dateExpiration = (foundMedicament.getDateExpiration() != null) ? foundMedicament.getDateExpiration().toString() : "Non disponible";
        float prixUnitaire = foundMedicament.getPrixUnitaire();
        String fournisseur = foundMedicament.getFournisseur();

        String comboBoxValue = comb.getValue();
        String sonId = "Id";

        if (Objects.equals(comboBoxValue, sonId)) {
            affiche.setText("Son id est : " + id);
        }else if (comboBoxValue.equals("Dose")) {
            affiche.setText("Sa dose est : " + doseMaxJournaliere);
        }else if (comboBoxValue.equals("Population")){
            affiche.setText("Sa population cible est : " + populationsCibles);
        } else if (comboBoxValue.equals("Complete")){
            affiche.setText("Informations du Médicament\n" + "Nom : " + nom + "\n" + "Substance Active : " + (substanceActive != null ? substanceActive : "Non spécifiée") + "\n" + "Dose Maximale Journalière : " + doseMaxJournaliere + " mg\n" +
                        "Dose Mortelle : " + doseMortelle + " mg\n" +
                        "Populations Cibles : " + populationsCibles + "\n" +
                        "Populations Contre-indiquées : " + populationsContreIndiquees + "\n" +
                        "Fréquence Maximale : " + (frequenceMaximale != null ? frequenceMaximale : "Non spécifiée") + "\n" +
                        "Durée Maximale du Traitement : " + dureeMaximaleTraitement + " jours\n" +
                        "Effets Secondaires : " + (effetsSecondaires != null ? effetsSecondaires : "Aucun signalé") + "\n" +
                        "Interactions : " + (interactions != null ? interactions : "Non spécifiées") + "\n" +
                        "Forme : " + (forme != null ? forme : "Non spécifiée") + "\n" +
                        "Stock Disponible : " + stockDisponible + " unités\n" +
                        "Date d'Expiration : " + dateExpiration + "\n" +
                        "Prix Unitaire : " + prixUnitaire + " €\n" +
                        "Fournisseur : " + (fournisseur != null ? fournisseur : "Non spécifié"));
        } else {
            affiche.setText("Veuillez choisir les informations que vous voulez voir");
        }



        // Affichage des informations
//        affiche.setText("Informations du Médicament\n" + "Nom : " + nom + "\n" + "Substance Active : " + (substanceActive != null ? substanceActive : "Non spécifiée") + "\n" + "Dose Maximale Journalière : " + doseMaxJournaliere + " mg\n" +
//                        "Dose Mortelle : " + doseMortelle + " mg\n" +
//                        "Populations Cibles : " + populationsCibles + "\n" +
//                        "Populations Contre-indiquées : " + populationsContreIndiquees + "\n" +
//                        "Fréquence Maximale : " + (frequenceMaximale != null ? frequenceMaximale : "Non spécifiée") + "\n" +
//                        "Durée Maximale du Traitement : " + dureeMaximaleTraitement + " jours\n" +
//                        "Effets Secondaires : " + (effetsSecondaires != null ? effetsSecondaires : "Aucun signalé") + "\n" +
//                        "Interactions : " + (interactions != null ? interactions : "Non spécifiées") + "\n" +
//                        "Forme : " + (forme != null ? forme : "Non spécifiée") + "\n" +
//                        "Stock Disponible : " + stockDisponible + " unités\n" +
//                        "Date d'Expiration : " + dateExpiration + "\n" +
//                        "Prix Unitaire : " + prixUnitaire + " €\n" +
//                        "Fournisseur : " + (fournisseur != null ? fournisseur : "Non spécifié"));

        System.out.println(foundMedicament);
        System.out.println("hey");
    }
}
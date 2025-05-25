package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.MedicamentDAO;
import com.example.suivipatientjavafx.model.Medicament;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicamentController extends Application implements Initializable, javafx.event.EventHandler<javafx.scene.input.MouseEvent> {

    @FXML
    private TextField medicamentFeld;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private TableView<Medicament> tableView;

    @FXML
    private TextField editNom;

    @FXML
    private TextField editSubstance;

    private final MedicamentDAO medicamentDAO = new MedicamentDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Activer l'édition sur la TableView
        tableView.setEditable(true);


        // Configurer les options de la ComboBox
        ObservableList<String> options = FXCollections.observableArrayList("Id", "Dose", "Population", "Complete", "All");
        comb.setItems(options);

        // Configurer les colonnes de la TableView pour l'édition

        // Colonne ID
        TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.<Medicament, Integer>forTableColumn(new IntegerStringConverter()));
        idColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Nom
        TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Substance Active
        TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
        substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));
        substanceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        substanceColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSubstanceActive(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Dose Maximale Journalière
        TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
        doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));
        doseMaxColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        doseMaxColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDoseMaxJournaliere(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Dose Mortelle
        TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
        doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));
        doseMortelleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        doseMortelleColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDoseMortelle(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Populations Cibles
        TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
        popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));
        popCiblesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        popCiblesColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPopulationsCibles(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Populations Contre-indiquées
        TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
        popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));
        popContreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        popContreColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPopulationsContreIndiquees(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Fréquence Maximale
        TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
        frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));
        frequenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        frequenceColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFrequenceMaximale(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Durée Maximale Traitement
        TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));
        dureeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dureeColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDureeMaximaleTraitement(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Effets Secondaires
        TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
        effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));
        effetsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        effetsColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEffetsSecondaires(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Interactions
        TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
        interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));
        interactionsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        interactionsColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setInteractions(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Forme
        TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
        formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));
        formeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        formeColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setForme(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Stock Disponible
        TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));
        stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setStockDisponible(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Date Expiration


        TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");

// Convertir Timestamp en String pour l'affichage
        dateExpColumn.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getDateExpiration();
            String formattedDate = (date != null) ? new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(date.getTime())) : "";
            return new SimpleStringProperty(formattedDate);
        });

        dateExpColumn.setCellFactory(TextFieldTableCell.forTableColumn());

// Gérer la conversion de String vers Timestamp lorsqu'on édite la cellule
        dateExpColumn.setOnEditCommit(event -> {
            Medicament medicament = event.getRowValue();
            String newValue = event.getNewValue();

            try {
                Timestamp newTimestamp = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(newValue).getTime());
                medicament.setDateExpiration(newTimestamp);
                medicamentDAO.updateMedicament(medicament);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // Colonne Prix Unitaire
        TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        prixColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrixUnitaire(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Colonne Fournisseur
        TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        fournisseurColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fournisseurColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFournisseur(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        // Ajouter toutes les colonnes à la TableView
        tableView.getColumns().addAll(
                idColumn, nomColumn, substanceColumn, doseMaxColumn, doseMortelleColumn,
                popCiblesColumn, popContreColumn, frequenceColumn, dureeColumn, effetsColumn,
                interactionsColumn, formeColumn, stockColumn, dateExpColumn, prixColumn, fournisseurColumn
        );

        // Charger les données initiales
        displayAllMedications();
    }

    private void displayAllMedications() {
        ObservableList<Medicament> allMedications = medicamentDAO.getAllMedicaments();
        tableView.setItems(allMedications);
    }

    @FXML
    public void btnMedicament(ActionEvent event) {
        String selectedOption = comb.getValue();

        if (selectedOption == null) {
            displayAllMedications();
            return;
        }

        if (selectedOption.equals("All")) {
            displayAllMedications();
        } else {
            String medicamentName = medicamentFeld.getText();
            Medicament med = medicamentDAO.getMedicamentByName(medicamentName);

            if (med == null) {
                tableView.getColumns().clear();
                tableView.setItems(FXCollections.observableArrayList());
            } else {
                updateTableForOption(selectedOption, med);
            }
        }
    }

    public void displayTraitements(ActionEvent event) {

    }

    private void updateTableForOption(String option, Medicament med) {
        tableView.getColumns().clear();
        ObservableList<Medicament> data = FXCollections.observableArrayList();
        data.add(med);

        switch (option) {
            case "Id": {

                TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                tableView.getColumns().add(idColumn);
                break;
            }
            case "Dose": {
                TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
                doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));
                doseMaxColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
                doseMaxColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setDoseMaxJournaliere(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });
                tableView.getColumns().add(doseMaxColumn);
                break;
            }
            case "Population": {
                TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
                popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));
                popCiblesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                popCiblesColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setPopulationsCibles(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });
                tableView.getColumns().add(popCiblesColumn);
                break;
            }
            case "Complete": {
                // Colonne ID
                TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                idColumn.setCellFactory(TextFieldTableCell.<Medicament, Integer>forTableColumn(new IntegerStringConverter()));
                idColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Nom
                TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                nomColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Substance Active
                TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
                substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));
                substanceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                substanceColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setSubstanceActive(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Dose Mortelle
                TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
                doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));
                doseMortelleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
                doseMortelleColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setDoseMortelle(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });



                // Colonne Dose Maximale Journalière
                TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
                doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));
                doseMaxColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
                doseMaxColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setDoseMaxJournaliere(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Populations Cibles
                TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
                popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));
                popCiblesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                popCiblesColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setPopulationsCibles(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Populations Contre-indiquées
                TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
                popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));
                popContreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                popContreColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setPopulationsContreIndiquees(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Fréquence Maximale
                TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
                frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));
                frequenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                frequenceColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setFrequenceMaximale(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Durée Maximale Traitement
                TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
                dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));
                dureeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                dureeColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setDureeMaximaleTraitement(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Effets Secondaires
                TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
                effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));
                effetsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                effetsColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEffetsSecondaires(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Interactions
                TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
                interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));
                interactionsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                interactionsColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setInteractions(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Forme
                TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
                formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));
                formeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                formeColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setForme(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Stock Disponible
                TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
                stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));
                stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                stockColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setStockDisponible(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Date Expiration


                TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");

// Convertir Timestamp en String pour l'affichage
                dateExpColumn.setCellValueFactory(cellData -> {
                    Date date = cellData.getValue().getDateExpiration();
                    String formattedDate = (date != null) ? new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(date.getTime())) : "";
                    return new SimpleStringProperty(formattedDate);
                });

                dateExpColumn.setCellFactory(TextFieldTableCell.forTableColumn());

// Gérer la conversion de String vers Timestamp lorsqu'on édite la cellule
                dateExpColumn.setOnEditCommit(event -> {
                    Medicament medicament = event.getRowValue();
                    String newValue = event.getNewValue();

                    try {
                        Timestamp newTimestamp = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(newValue).getTime());
                        medicament.setDateExpiration(newTimestamp);
                        medicamentDAO.updateMedicament(medicament);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


                // Colonne Prix Unitaire
                TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
                prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
                prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
                prixColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrixUnitaire(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Fournisseur
                TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
                fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
                fournisseurColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                fournisseurColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setFournisseur(e.getNewValue());
                    medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                tableView.getColumns().addAll(
                        idColumn, nomColumn, substanceColumn, doseMaxColumn, doseMortelleColumn,
                        popCiblesColumn, popContreColumn, frequenceColumn, dureeColumn, effetsColumn,
                        interactionsColumn, formeColumn, stockColumn, dateExpColumn, prixColumn, fournisseurColumn
                );
                break;
            }
            default:
                break;
        }
        tableView.setItems(data);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/suivipatientjavafx/medicament.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Suivi Patient - Médicaments");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
        editNom.setText(" " + tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex()).getNom());
    }
}
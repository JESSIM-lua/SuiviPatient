package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.MedicamentDAO;
import com.example.suivipatientjavafx.dao.PatientsDAO;
import com.example.suivipatientjavafx.dao.TraitementsDAO;
import com.example.suivipatientjavafx.model.Medicament;
import com.example.suivipatientjavafx.model.Patients;
import com.example.suivipatientjavafx.model.Traitements;
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
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import javax.persistence.Table;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientsController extends Application implements Initializable {
    @FXML
    private TextField medicamentFeld;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private TableView<Patients> tableView;

    @FXML
    private TableView<Patients> patientsTableView;

    @FXML
    private TableView<Traitements> traitementsTableView;

    private final PatientsDAO patientsDAO = new PatientsDAO();
    private final TraitementsDAO traitementsDAO = new TraitementsDAO();

    private ObservableList<Patients> data = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientsTableView.setEditable(true);
        traitementsTableView.setEditable(false); // Les traitements ne seront pas modifiables
        ObservableList<String> options = FXCollections.observableArrayList("Complete", "All");
        comb.setItems(options);

        TableColumn<Patients, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Patients, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(e -> {
            e.getRowValue().setNom(e.getNewValue());
            patientsDAO.savePatient(e.getRowValue());
        });

        TableColumn<Patients, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        patientsTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn);

        ObservableList<Patients> allPatients = patientsDAO.getAllPatients();
        patientsTableView.setItems(allPatients);

        patientsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayTraitementsForPatient(newSelection.getId());
            }
        });

        setupTraitementsTable();
    }

    private void displayTraitementsForPatient(int patientId) {
        List<Traitements> traitementsList = traitementsDAO.getPatientTraitement(patientId);
        ObservableList<Traitements> traitementsData = FXCollections.observableArrayList(traitementsList);
        traitementsTableView.setItems(traitementsData);
    }


    private void setupTraitementsTable() {
        traitementsTableView.getColumns().clear();

        TableColumn<Traitements, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Traitements, String> dosageColumn = new TableColumn<>("Dosage");
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        TableColumn<Traitements, Integer> dureeColumn = new TableColumn<>("Durée (jours)");
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree_jours"));

        TableColumn<Traitements, String> frequenceColumn = new TableColumn<>("Fréquence");
        frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequence"));

        TableColumn<Traitements, String> dateDebutColumn = new TableColumn<>("Date Début");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("date_debut"));

        TableColumn<Traitements, String> dateFinColumn = new TableColumn<>("Date Fin");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        TableColumn<Traitements, String> recommandationsColumn = new TableColumn<>("Recommandations");
        recommandationsColumn.setCellValueFactory(new PropertyValueFactory<>("recommandations"));

        traitementsTableView.getColumns().addAll(idColumn, dosageColumn, dureeColumn, frequenceColumn, dateDebutColumn, dateFinColumn);
    }



    private void displayAllMedications() {
        ObservableList<Patients> allMedications = patientsDAO.getAllPatients();
        patientsTableView.setItems(allMedications);
    }

    @FXML
    public void btnPatients(ActionEvent event) {
        String selectedOption = comb.getValue();

        if (selectedOption == null) {
            displayAllMedications();
            return;
        }

        if (selectedOption.equals("All")) {
            displayAllMedications();
        } else {
            String medicamentName = medicamentFeld.getText();
            Patients med = patientsDAO.getPatientById(Integer.parseInt(medicamentName));

            if (med == null) {
                patientsTableView.getColumns().clear();
                patientsTableView.setItems(FXCollections.observableArrayList());
            } else {
                updateTableForOption(selectedOption, med);

            }
        }
    }

    private void displayTraitementByPatientId(int id) {
        Patients pat = patientsDAO.getPatientById(id);
        updateTableForOption("Traitements", pat);

    }

    private void updateTableForOption(String option, Patients pat) {
        patientsTableView.getColumns().clear();
        ObservableList<Patients> data = FXCollections.observableArrayList();
        data.add(pat);

        switch (option) {

            case "Complete": {
                    TableColumn<Patients, Integer> idColumn = new TableColumn<>("Id");
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                idColumn.setCellFactory(TextFieldTableCell.<Patients, Integer>forTableColumn(new IntegerStringConverter()));
                idColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Nom
                TableColumn<Patients, String> nomColumn = new TableColumn<>("Nom");
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                nomColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Prénom
                TableColumn<Patients, String> prenomColumn = new TableColumn<>("Prénom");
                prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                prenomColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                StringConverter<Date> dateConverter = new StringConverter<Date>() {
                    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    @Override
                    public String toString(Date date) {
                        return date != null ? dateFormat.format(date) : "";
                    }

                    @Override
                    public Date fromString(String string) {
                        try {
                            return dateFormat.parse(string);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                };

                // Colonne Date de Naissance
                TableColumn<Patients, Date> dateNaissanceColumn = new TableColumn<>("Date de Naissance");
                dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
                dateNaissanceColumn.setCellFactory(TextFieldTableCell.forTableColumn(dateConverter));
                dateNaissanceColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setDateNaissance(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Téléphone
                TableColumn<Patients, String> telephoneColumn = new TableColumn<>("Téléphone");
                telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                telephoneColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setTelephone(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                // Colonne Email
                TableColumn<Patients, String> emailColumn = new TableColumn<>("Email");
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                emailColumn.setOnEditCommit(e -> {
                    e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
                    patientsDAO.savePatient(e.getTableView().getItems().get(e.getTablePosition().getRow()));
                });

                patientsTableView.getColumns().addAll(
                        idColumn, nomColumn, prenomColumn, telephoneColumn,
                        emailColumn, dateNaissanceColumn
                );

                break;
            }
            default:
                break;
        }
        patientsTableView.setItems(data);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/suivipatientjavafx/Patients.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Suivi Patient - Médicaments");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}

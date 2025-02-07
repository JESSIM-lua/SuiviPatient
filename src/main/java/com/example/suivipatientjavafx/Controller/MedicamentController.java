package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.MedicamentDAO;
import com.example.suivipatientjavafx.model.Medicament;
import javafx.application.Application;
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

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicamentController extends Application implements Initializable, javafx.event.EventHandler<javafx.scene.input.MouseEvent> {

    @FXML
    private TextField medicamentFeld;

    @FXML
    private ComboBox<String> comb;

    @FXML
    public void getwSelected(MouseEvent event){
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        editNom.setText(" "+tableView.getItems().get(index).getNom());
    }




    @FXML
    private TableView<Medicament> tableView;

    @FXML
    private TextField editNom;

    @FXML
    private TextField editSubstance;


    private final MedicamentDAO medicamentDAO = new MedicamentDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("Id", "Dose", "Population", "Complete", "All");
        comb.setItems(options);
        TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.<Medicament, Integer>forTableColumn(new IntegerStringConverter()));


        TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });




        TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
        substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));
        substanceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
        doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));

        TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
        doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));

        TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
        popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));

        TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
        popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));

        TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
        frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));

        TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));

        TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
        effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));

        TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
        interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));

        TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
        formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));

        TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));

        TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");
        dateExpColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));

        TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

        tableView.getColumns().addAll(idColumn, nomColumn, substanceColumn, doseMaxColumn, doseMortelleColumn, popCiblesColumn,
                popContreColumn, frequenceColumn, dureeColumn, effetsColumn, interactionsColumn, formeColumn, stockColumn,
                dateExpColumn, prixColumn, fournisseurColumn);


        displayAllMedications();
        editData();


    }



    /**
     * Méthode appelée lors du clic sur le bouton (défini dans le FXML) pour lancer la recherche/filtrage.
     * Selon l'option choisie, le TableView est mis à jour avec les données correspondantes.
     */
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

    /**
     * Affiche tous les médicaments dans le TableView sous forme d'une vue "résumé"
     * avec 5 colonnes : Id, Nom, Substance Active, Dose Maximale Journalière, Dose Mortelle.
     */
    private void displayAllMedications() {

        TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.<Medicament, Integer>forTableColumn(new IntegerStringConverter()));


        TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            medicamentDAO.updateMedicament(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });


        TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
        substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));
        substanceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
        doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));

        TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
        doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));

        TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
        popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));

        TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
        popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));

        TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
        frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));

        TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));

        TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
        effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));

        TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
        interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));

        TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
        formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));

        TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));

        TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");
        dateExpColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));

        TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

        tableView.getColumns().addAll(idColumn, nomColumn, substanceColumn, doseMaxColumn, doseMortelleColumn, popCiblesColumn,
                popContreColumn, frequenceColumn, dureeColumn, effetsColumn, interactionsColumn, formeColumn, stockColumn,
                dateExpColumn, prixColumn, fournisseurColumn);
        ObservableList<Medicament> allMedications = medicamentDAO.getAllMedicaments();
        tableView.setItems(allMedications);
    }

    /**
     * Met à jour le TableView pour n'afficher que le médicament trouvé, avec
     * des colonnes adaptées à l'option choisie.
     *
     * @param option L'option choisie dans la ComboBox ("Id", "Dose", "Population", "Complete").
     * @param med    Le médicament trouvé correspondant au nom saisi.
     */
    private void updateTableForOption(String option, Medicament med) {
        tableView.getColumns().clear();
        ObservableList<Medicament> data = FXCollections.observableArrayList();
        data.add(med);

        switch (option) {
            case "Id": {
                TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableView.getColumns().add(idColumn);
                break;
            }
            case "Dose": {
                TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
                doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));
                doseMaxColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
                tableView.getColumns().add(doseMaxColumn);
                break;
            }
            case "Population": {
                TableColumn<Medicament, String> popColumn = new TableColumn<>("Population Cible");
                popColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));
                tableView.getColumns().add(popColumn);
                break;
            }
            case "Complete": {
                TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

                TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
                substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));

                TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
                doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));

                TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
                doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));

                TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
                popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));


                TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
                popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));

                TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
                frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));

                TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
                dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));

                TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
                effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));

                TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
                interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));

                TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
                formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));

                TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
                stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));

                TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");
                dateExpColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));

                TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
                prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

                TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
                fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

                tableView.getColumns().addAll(idColumn, nomColumn, substanceColumn, doseMaxColumn, doseMortelleColumn,
                        popCiblesColumn, popContreColumn, frequenceColumn, dureeColumn, effetsColumn,
                        interactionsColumn, formeColumn, stockColumn, dateExpColumn, prixColumn, fournisseurColumn);
                break;
            }
            default:
                break;
        }
        tableView.setItems(data);
    }

    public void editData(){
        TableColumn<Medicament, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellFactory(TextFieldTableCell.<Medicament, Integer>forTableColumn(new IntegerStringConverter()));




        TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Medicament, String> substanceColumn = new TableColumn<>("Substance Active");
        substanceColumn.setCellValueFactory(new PropertyValueFactory<>("substanceActive"));


        TableColumn<Medicament, Float> doseMaxColumn = new TableColumn<>("Dose Maximale Journalière");
        doseMaxColumn.setCellValueFactory(new PropertyValueFactory<>("doseMaxJournaliere"));

        TableColumn<Medicament, Float> doseMortelleColumn = new TableColumn<>("Dose Mortelle");
        doseMortelleColumn.setCellValueFactory(new PropertyValueFactory<>("doseMortelle"));

        TableColumn<Medicament, String> popCiblesColumn = new TableColumn<>("Populations Cibles");
        popCiblesColumn.setCellValueFactory(new PropertyValueFactory<>("populationsCibles"));

        TableColumn<Medicament, String> popContreColumn = new TableColumn<>("Populations Contre-indiquées");
        popContreColumn.setCellValueFactory(new PropertyValueFactory<>("populationsContreIndiquees"));

        TableColumn<Medicament, String> frequenceColumn = new TableColumn<>("Fréquence Maximale");
        frequenceColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceMaximale"));

        TableColumn<Medicament, Integer> dureeColumn = new TableColumn<>("Durée Maximale Traitement");
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("dureeMaximaleTraitement"));

        TableColumn<Medicament, String> effetsColumn = new TableColumn<>("Effets Secondaires");
        effetsColumn.setCellValueFactory(new PropertyValueFactory<>("effetsSecondaires"));

        TableColumn<Medicament, String> interactionsColumn = new TableColumn<>("Interactions");
        interactionsColumn.setCellValueFactory(new PropertyValueFactory<>("interactions"));

        TableColumn<Medicament, String> formeColumn = new TableColumn<>("Forme");
        formeColumn.setCellValueFactory(new PropertyValueFactory<>("forme"));

        TableColumn<Medicament, Integer> stockColumn = new TableColumn<>("Stock Disponible");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));

        TableColumn<Medicament, String> dateExpColumn = new TableColumn<>("Date Expiration");
        dateExpColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));

        TableColumn<Medicament, Float> prixColumn = new TableColumn<>("Prix Unitaire");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        TableColumn<Medicament, String> fournisseurColumn = new TableColumn<>("Fournisseur");
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

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

        editNom.setText(" "+tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex()).getNom());

    }
}

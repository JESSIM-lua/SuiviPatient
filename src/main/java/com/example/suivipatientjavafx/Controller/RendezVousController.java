package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.PatientsDAO;
import com.example.suivipatientjavafx.dao.RendezvousDAO;
import com.example.suivipatientjavafx.model.Patients;
import com.example.suivipatientjavafx.model.Rendezvous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

public class RendezVousController implements Initializable {

    @FXML
    private TableView<Rendezvous> tableViewRendezVous;

    @FXML
    private TableColumn<Rendezvous, Integer> colId;

    @FXML
    private TableColumn<Rendezvous, Integer> colPatientId;

    @FXML
    private TableColumn<Rendezvous, Integer> colUtilisateurId;

    @FXML
    private TableColumn<Rendezvous, String> colDateRendezvous;

    @FXML
    private TableColumn<Rendezvous, String> colMotif;

    @FXML
    private TableColumn<Rendezvous, String> colEtat;

    @FXML
    private TableColumn<Rendezvous, String> colDateCreation;

    @FXML
    private TableColumn<Rendezvous, String> colPatientName;

    @FXML
    private Button AddBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private ComboBox<String> ComboState;

    @FXML
    private Button SearchState;

    private final PatientsDAO patientsDAO = new PatientsDAO();


    private <T> void makeColumnEditable(TableColumn<Rendezvous, T> column, Function<Rendezvous, Consumer<T>> setterFunction) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.StringConverter<>() {
            @Override
            public String toString(T object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public T fromString(String string) {
                // Laisse Hibernate gérer les conversions de types : si ça échoue, la base rejettera
                try {
                    return (T) string;
                } catch (Exception e) {
                    return null;
                }
            }
        }));

        column.setOnEditCommit(event -> {
            Rendezvous rv = event.getRowValue();
            setterFunction.apply(rv).accept(event.getNewValue());

            try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
                session.beginTransaction();
                session.update(rv);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Configuration des colonnes
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
//        Integer idPat = colPatientId.getCellData(tableViewRendezVous.getSelectionModel().getSelectedItem());
//        Patients pat = patientsDAO.getPatientById(idPat);
        colPatientName.setCellValueFactory(cellData -> {
            Rendezvous rendezvous = cellData.getValue();
            Patients patient = patientsDAO.getPatientById(rendezvous.getPatient_id());
            return patient != null
                    ? new javafx.beans.property.SimpleStringProperty(patient.getNom() + " " + patient.getPrenom())
                    : new javafx.beans.property.SimpleStringProperty("Inconnu");
        });


//        colUtilisateurId.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
        colDateRendezvous.setCellValueFactory(new PropertyValueFactory<>("date_rendezvous"));
        colDateRendezvous.setCellFactory(column -> {
            TableCell<Rendezvous, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                    }
                }
            };
            return cell;
        });
        colMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colEtat.setCellFactory(column -> {
            TableCell<Rendezvous, String> cell = new TableCell<>() {
                private final ComboBox<String> comboBox = new ComboBox<>();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        comboBox.getItems().setAll("planifié", "annulé", "effectué");
                        comboBox.setValue(item);
                        comboBox.setOnAction(event -> {
                            String newValue = comboBox.getValue();
                            Rendezvous rendezvous = getTableView().getItems().get(getIndex());
                            rendezvous.setEtat(newValue);

                            try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
                                session.beginTransaction();
                                session.update(rendezvous);
                                session.getTransaction().commit();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        setGraphic(comboBox);
                    }
                }
            };
            return cell;
        });
        colDateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colMotif.setEditable(true);

        colMotif.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        makeColumnEditable(colMotif, rv -> val -> rv.setMotif(val));
        tableViewRendezVous.setEditable(true);
        tableViewRendezVous.setPlaceholder(new Label("Aucun rendez-vous trouvé"));

        // Configuration de la ComboBox
        ComboState.getItems().addAll("All", "planifié", "annulé", "effectué");
        ComboState.setValue("All");


        // Charger les données depuis Hibernate
        loadRendezVous();
    }

    private void loadRendezVous() {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Rendezvous.class)
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            List<Rendezvous> results = session.createQuery("FROM Rendezvous", Rendezvous.class).getResultList();
            ObservableList<Rendezvous> rendezVousList = FXCollections.observableArrayList(results);
            tableViewRendezVous.setItems(rendezVousList);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void displayPlanifiedRdv(String etat) {
        List<Rendezvous> rendezVousList = RendezvousDAO.getByEtat(etat);
        assert rendezVousList != null;
        ObservableList<Rendezvous> observableList = FXCollections.observableArrayList(rendezVousList);
        tableViewRendezVous.setItems(observableList);
        tableViewRendezVous.refresh();



    }

    @FXML
    private void searchState(ActionEvent event) {
        String selectedState = ComboState.getValue();

        if (selectedState == null || selectedState.equals("All")) {
            loadRendezVous();
        } else {
            displayPlanifiedRdv(selectedState);
        }
    }

    @FXML
    private void addRendezVous(ActionEvent event) {

    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        try {
            String role = com.example.suivipatientjavafx.util.Session.getCurrentUserRole(); // Méthode à créer (voir ci-dessous)
            String fxml;

            switch (role) {
                case "secretaire":
                    fxml = "/com/example/suivipatientjavafx/dashboardSecretaire.fxml";
                    break;
                case "admin":
                case "medecin":
                    fxml = "/com/example/suivipatientjavafx/dashboard.fxml";
                    break;
                default:
                    throw new IllegalArgumentException("Rôle inconnu : " + role);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) tableViewRendezVous.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

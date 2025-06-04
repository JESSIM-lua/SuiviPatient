package com.example.suivipatientjavafx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardSecController implements Initializable {
    @FXML
    private ComboBox<String> comb;

    @FXML
    private Label label;
    private String page;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        comboBox.setItems(FXCollections.observableArrayList("A", "B", "C"));
        ObservableList<String> items = FXCollections.observableArrayList("rendezVous", "Patients");

        comb.setItems(items);

    }


    public void loadPage(ActionEvent event, String page ) {
        try {
            String fxmlPath = "/com/example/suivipatientjavafx/" + page + ".fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectItem(ActionEvent actionEvent) {
        label.setText(comb.getValue());
        loadPage(actionEvent, comb.getValue());

    }

}

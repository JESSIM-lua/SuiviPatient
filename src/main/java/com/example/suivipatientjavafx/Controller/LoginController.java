package com.example.suivipatientjavafx.Controller;

import com.example.suivipatientjavafx.dao.UtilisateursDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final UtilisateursDAO utilisateursDAO = new UtilisateursDAO();

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs !");
            return;
        }

        boolean isValid = utilisateursDAO.validateLogin(email, password);

        if (isValid && utilisateursDAO.getRoleByEmail(email).equals("medecin")) {
            errorLabel.setText("Connexion réussie !");
            loadDashboard(event);
        } else if (isValid && utilisateursDAO.getRoleByEmail(email).equals("secretaire")) {
            errorLabel.setText("Connexion réussie !");
            loadSec(event);

        } else {
            errorLabel.setText("Identifiants incorrects !");

        }
    }

    public void loadDashboard(ActionEvent event) {
        try {
            String fxmlPath = "/com/example/suivipatientjavafx/dashboard.fxml";
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
    public void loadSec(ActionEvent event) {
        try {
            String fxmlPath = "/com/example/suivipatientjavafx/dashboardSecretaire.fxml";
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


    @FXML
    public void goToRegister(ActionEvent event) {
        try {
            String fxmlPath = "/com/example/suivipatientjavafx/register.fxml";
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
}

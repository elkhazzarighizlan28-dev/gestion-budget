package com.budgetmanager.controller;


import com.budgetmanager.database.DatabaseConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CreateAccountController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label loginLabel;

    @FXML
    private void handleCreateAccount() {

        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (fullName.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {

            showAlert("Error", "Please fill in all fields!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match!");
            return;
        }

        String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, password);

            statement.executeUpdate();

            showAlert("Success", "Account created successfully!");

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                showAlert("Error", "This email is already registered!");
            } else {
                e.printStackTrace();
                showAlert("Error", "Unable to create account!");
            }
        }
    }

   @FXML
private void handleLogin() {
    try {
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/budgetmanager/view/Login.fxml")
        );

        Stage stage = (Stage) loginLabel.getScene().getWindow();

        stage.setScene(new Scene(root, 1000, 650));
        stage.setTitle("Budget Manager - Login");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
package com.budgetmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.budgetmanager.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label createAccountLabel;

    @FXML
    private void handleLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill in all fields!");
        } else {
    System.out.println("Login clicked!");
    System.out.println("Email: " + email);

    try (Connection connection = DatabaseConnection.getConnection()) {
        System.out.println("MySQL Connection Successful!");
    } catch (SQLException e) {
        System.out.println("MySQL Connection Failed!");
        e.printStackTrace();
    }
}
        
    }

    @FXML
   
private void handleCreateAccount() {
    try {
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/budgetmanager/view/CreateAccount.fxml")
        );

        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 650));
        stage.setTitle("Budget Manager - Create Account");

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
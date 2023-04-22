package com.mancala.mancalagame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OpponentSelectionController {

    @FXML
    private Button computerButton;

    @FXML
    private Button humanButton;

    @FXML
    private void handleComputerOpponent() {
        // Start the game against the computer
    }

    @FXML
    private void handleHumanOpponent() {
        // Load the login page to prompt the user to log in
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) humanButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectionChanged() {
        // Enable or disable the Start button depending on whether a Button is selected or not
    }
}

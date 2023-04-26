package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ComputerPlayerSelectController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button open;

    public void CPU(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gamemode.fxml"));
        Stage stage = (Stage)open.getScene().getWindow();
        stage.setScene(new Scene(root, 750, 500));
    }

    public void player(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("playerLogIn.fxml"));
        Stage stage = (Stage)open.getScene().getWindow();
        stage.setScene(new Scene(root, 750, 500));
    }
}

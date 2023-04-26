package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameDashboardController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label welcome_user;
    @FXML
    private Button gameMode;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event,"playerLogIn.fxml", "log in", null);
            }
        });
    }
    public void setUserInformation(String username){
        welcome_user.setText("Welcome to the Game: "+ username);
    }
    public void play(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gamemode.fxml"));
        Stage stage = (Stage)gameMode.getScene().getWindow();
        stage.setScene(new Scene(root, 750, 500));
    }
}

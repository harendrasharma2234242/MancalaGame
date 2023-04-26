package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GameDashboardController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label welcome_user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "PlayerLogIn.fxml", "Opponent log in", null);
            }
        });
    }

    public void setUserInformation(String username) {
        welcome_user.setText("Welcome to the Game: " + username);
    }
}

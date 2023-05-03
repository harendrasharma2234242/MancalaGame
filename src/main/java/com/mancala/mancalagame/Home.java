package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Button btn_player;
    @FXML
    private Button btn_admin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "PlayerLogIn.fxml", "Player Log in", null, null, null, null);
            }
        });
        btn_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "AdminLogin.fxml", "Admin log in", null, null, null, null);
            }
        });
    }
}
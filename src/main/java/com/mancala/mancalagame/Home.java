package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for homescreen where you can log in as admin or player
 * @author Harendra Sharma
 * @version 1.0
 */
public class Home implements Initializable {

    @FXML
    private Button btn_player;
    @FXML
    private Button btn_admin;

    /**
     * Initialise the scene and set button functionality.
     * @param url
     * @param resourceBundle
     */
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
package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AdminDashboard implements Initializable {


    @FXML
    private Button new_users;

    @FXML
    private Button frequent_users;

    @FXML
    private Button powerup;

    @FXML
    private Button back;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "newUserRequests.fxml","New User Requests", null);
            }
        });

        frequent_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "frequentUsers.fxml","Frequent Users", null);
            }
        });

        powerup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "powerupStats.fxml","Powerup/Special Stone Statistics", null);
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "home.fxml","Home", null);
            }
        });
    }
}



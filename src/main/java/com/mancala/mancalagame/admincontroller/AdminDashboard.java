package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.AdminBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller class for the admin dashboard scene.
 * @author Harendra Sharma
 * @version 1.0
 */
public class AdminDashboard implements Initializable {
    @FXML
    private Button new_users;
    @FXML
    private Button frequent_users;
    @FXML
    private Button powerup;
    @FXML
    private Button logOut;
    private String userName;

    /**
     * Initialise the scene. Set button functions.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<List<String>> newUsers = AdminBean.newUsers();
                AdminBean.changeScene(event, "newUserRequests.fxml","New User Requests", userName, newUsers);
            }
        });

        frequent_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.changeScene(event, "frequentUsers.fxml","Frequent Users", userName, null);

            }
        });

        powerup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.changeScene(event, "powerupStats.fxml","Powerup/Special Stone Statistics", userName, null);
            }
        });

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.changeScene(event, "Home.fxml","Home", null, null);
            }
        });
    }

    /**
     * Retrieve the username of the current player.
     * @param userName player's username
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
}



package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.AdminBean;
import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.OpponentLogInBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller for player profile
 * @author Harendra Sharma
 * @author Alex Wadge
 * @version 2.0
 */
public class PlayerProfileController implements Initializable{
    @FXML
    private Label playerUserName;
    @FXML
    private Label lastLogin;
    @FXML
    private Label finishedGame;
    @FXML
    private Label totalGames;
    @FXML
    private Label totalWins;
    @FXML
    private Label totalLoss;
    @FXML
    private Label winPosition;
    @FXML
    private Label percentPosition;
    @FXML
    private Label winPercentage;
    @FXML
    private Button favePlayers;
    @FXML
    private Button back;
    private static String name;

    /**
     * Updates profile data.
     * @param username current player's username.
     */
    public void updateProfileData(String username){
        ArrayList<String> userData = UsersBean.getUserProfile(username);
        ArrayList<ArrayList<String>> wins = UsersBean.orderByWins();
        int winsPos = wins.get(0).indexOf(username)+1;
        ArrayList<ArrayList<String>> percent = UsersBean.orderByPercentage();
        int percentPos = percent.get(0).indexOf(username)+1;
        playerUserName.setText(userData.get(0));
        lastLogin.setText(userData.get(1));
        finishedGame.setText(userData.get(2));
        totalGames.setText(userData.get(2));
        totalWins.setText(userData.get(3));
        totalLoss.setText(userData.get(4));
        winPercentage.setText(String.valueOf(userData.get(5)));
        winPosition.setText(String.valueOf(winsPos));
        percentPosition.setText(String.valueOf(percentPos));
    }

    /**
     * Initialises the scene and sets button functionality.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateProfileData(name);
        favePlayers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "FavouriteUsers.fxml","Favourite Users", name,
                        null,null,null);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Home.fxml","Home", name, null, null, null);
            }
        });
    }

    /**
     * Retrieve current player's username.
     * @param username current player's username
     */
    public static void userName(String username) {
        name = username;
    }



}

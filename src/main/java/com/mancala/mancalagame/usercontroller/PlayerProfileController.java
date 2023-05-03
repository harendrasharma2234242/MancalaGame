package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private Label winPercentage;
    private static String name;

    public void updateProfileData(String username){
        ArrayList<String> userData = UsersBean.getUserProfile(username);
        for (String s : userData) {
            System.out.println(s);
        }
        playerUserName.setText(userData.get(0));
        lastLogin.setText(userData.get(1));
//        lastLogin.setText("last login");
        finishedGame.setText(userData.get(2));
        totalGames.setText(userData.get(2));
        totalWins.setText(userData.get(3));
        totalLoss.setText(userData.get(4));
        winPercentage.setText(String.valueOf(userData.get(5)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void test()  {
        updateProfileData(name);
    }

    public static void userName(String username) {
        name = username;
    }
}

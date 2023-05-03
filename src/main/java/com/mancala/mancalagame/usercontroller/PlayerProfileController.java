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
    @FXML
    private Button favePlayers;
    @FXML
    private Button back;
    private static String name;

    public void updateProfileData(String username){
        System.out.println(username);
        ArrayList<String> userData = UsersBean.getUserProfile(username);
        playerUserName.setText(userData.get(0));
        lastLogin.setText(userData.get(1));
        finishedGame.setText(userData.get(2));
        totalGames.setText(userData.get(2));
        totalWins.setText(userData.get(3));
        totalLoss.setText(userData.get(4));
        winPercentage.setText(String.valueOf(userData.get(5)));
    }

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
                OpponentAndGameModeBean.changeScene(event, "OpponentAndGameMode.fxml","Choose!", name, null, null);
            }
        });
    }

    @FXML
    public void test()  {
        updateProfileData(name);
    }

    public static void userName(String username) {
        name = username;
    }



}

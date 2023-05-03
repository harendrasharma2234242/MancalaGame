package com.mancala.mancalagame.usercontroller;

import com.almasb.fxgl.input.Input;
import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.Player;
import com.mancala.mancalagame.User;
import com.mancala.mancalagame.UsersBean;
import com.mancala.mancalagame.query.UsersQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    public Label usernameLabel;
    public Button favoriteButton;
    public ImageView profileImage;
    public Label winPercentageLabel;
    public CheckBox favoriteCheckBox;
    @FXML
    private GridPane leaderboardGridPane;

    private static String username;
    private static String sessionID;
    private static InputStream profileImageStream;
    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshLeaderboardTotalWins();

    }
    @FXML
    private void back() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "OpponentAndGameMode.fxml","Choose!", username, sessionID, profileImageStream, null);
            }
        });
    }

    @FXML
    private void refreshLeaderboardTotalWins() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());
        float totalWins;
        ArrayList<ArrayList<String>> listOfLists = UsersBean.orderByWins();
        ArrayList<String> orderedByTotalWins = listOfLists.get(0);
        ArrayList<String> wins = listOfLists.get(1);

        leaderboardGridPane.add(new Label("Profile image"), 0, 0);
        leaderboardGridPane.add(new Label("Username"), 1, 0);
        leaderboardGridPane.add(new Label("Total wins"), 2, 0);
        leaderboardGridPane.add(new Label("Add favourite"), 3, 0);
        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < orderedByTotalWins.size(); i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String user = orderedByTotalWins.get(i);
            totalWins = Float.parseFloat(wins.get(i));
//            ImageView profileImage = new ImageView("src/main/resources/images/ProfileImage.jpeg");
            CheckBox favoriteCheckBox1 = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(user);
            Label totalWinsLabel = new Label(String.valueOf(totalWins));


            leaderboardGridPane.add(profileImageLabel, 0, i+1);
            leaderboardGridPane.add(playerNameLabel, 1, i+1);
            leaderboardGridPane.add(totalWinsLabel, 2, i+1);
            leaderboardGridPane.add(favoriteCheckBox1, 3, i+1);
//            leaderboardGridPane.add(profileImage, 1, i+1);

            favoriteCheckBox1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (favoriteCheckBox1.isSelected()) {
                        UsersBean.addFavourite(username, user);
                    } else {
                        UsersBean.removeFavourite(username, user);
                    }
                }
            });
        }
    }

    @FXML
    private void refreshLeaderboardPercentage() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());

        float winPercentage;
        ArrayList<ArrayList<String>> listOfLists = UsersBean.orderByPercentage();
        ArrayList<String> orderedByPercentage = listOfLists.get(0);
        ArrayList<String> percents = listOfLists.get(1);

        leaderboardGridPane.add(new Label("Profile image"), 0, 0);
        leaderboardGridPane.add(new Label("Username"), 1, 0);
        leaderboardGridPane.add(new Label("Win percentage"), 2, 0);
        leaderboardGridPane.add(new Label("Add favourite"), 3, 0);
        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < orderedByPercentage.size(); i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String user = orderedByPercentage.get(i);
            winPercentage = Float.parseFloat(percents.get(i));
//            ImageView profileImage = new ImageView("src/main/resources/images/ProfileImage.jpeg");
            CheckBox favoriteCheckBox1 = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(user);
            Label winPercentageLabel = new Label(String.valueOf(winPercentage));

            leaderboardGridPane.add(profileImageLabel, 0, i+1);
            leaderboardGridPane.add(playerNameLabel, 1, i+1);
            leaderboardGridPane.add(winPercentageLabel, 2, i+1);
            leaderboardGridPane.add(favoriteCheckBox1, 3, i+1);
//            leaderboardGridPane.add(profileImage, 1, i+1);

            favoriteCheckBox1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (favoriteCheckBox1.isSelected()) {
                        UsersBean.addFavourite(username, user);
                    } else {
                        UsersBean.removeFavourite(username, user);
                    }
                }
            });
        }
    }

    public static void userName(String name, String session, InputStream image) {
        username = name;
        sessionID = session;
        profileImageStream = image;
    }

}

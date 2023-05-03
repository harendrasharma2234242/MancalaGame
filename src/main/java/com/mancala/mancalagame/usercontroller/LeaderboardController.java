package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.Player;
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

    @FXML
    private Button refreshButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshLeaderboardTotalWins();
        // Set up refresh button to refresh leaderboard when clicked
//        refreshButton.setOnAction(event -> refreshLeaderboard());
//        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                refreshLeaderboard();
//            }
//        });
    }

    @FXML
    private void refreshLeaderboardTotalWins() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());
        ArrayList<String> allUsers = UsersBean.getAllUsers();

        int totalWins;

        ArrayList<String> orderedByTotalWins = allUsers;
        ArrayList<Integer> wins = new ArrayList<>();

        for (String user : allUsers) {
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            System.out.println(user);
            wins.add(Integer.parseInt(userData.get(3)));
        }


        for (int i = 0; i < wins.size(); i++) {
            for (int j = 0; j < wins.size(); j++) {
                if (wins.get(i) > wins.get(j)) {
                    int tmp = wins.get(i);
                    wins.set(i,wins.get(j)) ;
                    wins.set(j,tmp);
                    String tmpUser = allUsers.get(i);
                    orderedByTotalWins.set(i, allUsers.get(j));
                    orderedByTotalWins.set(j, tmpUser);
                }
            }
        }
//        int i = 0;
        leaderboardGridPane.add(new Label("Profile image"), 0, 0);
        leaderboardGridPane.add(new Label("Username"), 1, 0);
        leaderboardGridPane.add(new Label("Total wins"), 2, 0);
        leaderboardGridPane.add(new Label("Add favourite"), 3, 0);
        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < orderedByTotalWins.size(); i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String user = orderedByTotalWins.get(i);
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            totalWins = wins.get(i);
//            ImageView profileImage = new ImageView("src/main/resources/images/ProfileImage.jpeg");
            CheckBox favoriteCheckBox = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(user);
            Label totalWinsLabel = new Label(String.valueOf(totalWins));


            leaderboardGridPane.add(profileImageLabel, 0, i+1);
            leaderboardGridPane.add(playerNameLabel, 1, i+1);
            leaderboardGridPane.add(totalWinsLabel, 2, i+1);
            leaderboardGridPane.add(favoriteCheckBox, 3, i+1);
//            leaderboardGridPane.add(profileImage, 1, i+1);
        }
    }

    @FXML
    private void refreshLeaderboardPercentage() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());
        ArrayList<String> allUsers = UsersBean.getAllUsers();
        float winPercentage;

        ArrayList<String> orderedByPercentage = allUsers;
        ArrayList<Float> percents = new ArrayList<>();

        for (String user : allUsers) {
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            percents.add(Float.valueOf(userData.get(5)));
        }

        for (int i = 0; i < percents.size(); i++) {
            for (int j = 0; j < percents.size(); j++) {
                if (percents.get(i) > percents.get(j)) {
                    float tmp = percents.get(i);
                    percents.set(i,percents.get(j)) ;
                    percents.set(j,tmp);
                    String tmpUser = allUsers.get(i);
                    orderedByPercentage.set(i, allUsers.get(j));
                    orderedByPercentage.set(j, tmpUser);
                }
            }
        }


        leaderboardGridPane.add(new Label("Profile image"), 0, 0);
        leaderboardGridPane.add(new Label("Username"), 1, 0);
        leaderboardGridPane.add(new Label("Total wins"), 2, 0);
        leaderboardGridPane.add(new Label("Add favourite"), 3, 0);
        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < orderedByPercentage.size(); i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String user = orderedByPercentage.get(i);
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            winPercentage = percents.get(i);
//            ImageView profileImage = new ImageView("src/main/resources/images/ProfileImage.jpeg");
            CheckBox favoriteCheckBox = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(user);
            Label winPercentageLabel = new Label(String.valueOf(winPercentage));


            leaderboardGridPane.add(profileImageLabel, 0, i+1);
            leaderboardGridPane.add(playerNameLabel, 1, i+1);
            leaderboardGridPane.add(winPercentageLabel, 2, i+1);
            leaderboardGridPane.add(favoriteCheckBox, 3, i+1);
//            leaderboardGridPane.add(profileImage, 1, i+1);
        }
    }
    @FXML
    private void handleFavoriteCheckboxAction(ActionEvent event) {
        // Here you would implement the logic to add/remove the selected player from
        // the user's favorites list in your backend or data source
        // For the sake of example, let's just print a message indicating which
        // player was favorite/unfavored

        CheckBox favoriteCheckBox = (CheckBox) event.getSource();
        String playerName = ((Label) favoriteCheckBox.getParent().getChildrenUnmodifiable().get(1)).getText();

        if (favoriteCheckBox.isSelected()) {
            System.out.println("Player " + playerName + " was favorite");
        } else {
            System.out.println("Player " + playerName + " was unfavored");
        }
    }


//    public void addFavourite() {
//        Player.addFavourite();
//    }
}

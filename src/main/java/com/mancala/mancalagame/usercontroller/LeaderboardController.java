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
        refreshLeaderboard();

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
    private void refreshLeaderboard() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());
        ArrayList<String> allUsers = UsersBean.getAllUsers();

        int totalWins;
        float winPercentage;

        ArrayList<String> orderedPercentage = allUsers;
        ArrayList<String> orderedTotalWins = allUsers;
        ArrayList<Integer> wins = new ArrayList<>();
        ArrayList<Float> percents = new ArrayList<>();

        for (String user : allUsers) {
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            System.out.println(user);
            wins.add(Integer.parseInt(userData.get(3)));
            percents.add(Float.valueOf(userData.get(5)));
        }

        for (int i = 0; i < percents.size(); i++) {
            for (int j = 0; j < percents.size(); j++) {
                if (percents.get(i) > percents.get(j)) {
                    float tmp = percents.get(i);
                    percents.set(i,percents.get(j)) ;
                    percents.set(j,tmp);
                    String tmpUser = allUsers.get(i);
                    orderedPercentage.set(i, allUsers.get(j));
                    orderedPercentage.set(j, tmpUser);
                }
            }
        }

        for (int i = 0; i < wins.size(); i++) {
            for (int j = 0; j < wins.size(); j++) {
                if (wins.get(i) > wins.get(j)) {
                    int tmp = wins.get(i);
                    wins.set(i,wins.get(j)) ;
                    wins.set(j,tmp);
                    String tmpUser = allUsers.get(i);
                    orderedTotalWins.set(i, allUsers.get(j));
                    orderedTotalWins.set(j, tmpUser);
                }
            }
        }
        System.out.println(percents);
        System.out.println(orderedPercentage);
//        int i = 0;
        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < 10; i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String user = orderedPercentage.get(i);
            ArrayList<String> userData = UsersBean.getUserProfile(user);
            winPercentage = percents.get(i);
//            ImageView profileImage = new ImageView("src/main/resources/images/ProfileImage.jpeg");
            CheckBox favoriteCheckBox = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(user);
            Label winPercentageLabel = new Label(String.valueOf(winPercentage));

            leaderboardGridPane.add(profileImageLabel, 0, i);
            leaderboardGridPane.add(playerNameLabel, 1, i);
            leaderboardGridPane.add(winPercentageLabel, 2, i);
            leaderboardGridPane.add(favoriteCheckBox, 3, i);
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

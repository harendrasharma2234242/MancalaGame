package com.mancala.mancalagame.usercontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    @FXML
    private GridPane leaderboardGridPane;

    @FXML
    private Button refreshButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshLeaderboard();

        // Set up refresh button to refresh leaderboard when clicked
        refreshButton.setOnAction(event -> refreshLeaderboard());
    }

    private void refreshLeaderboard() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
        leaderboardGridPane.getChildren().removeAll(leaderboardGridPane.getChildren());

        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < 10; i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below
            String playerName = "Player " + (i + 1);
            String winPercentage = "99%";
            ImageView profileImage = new ImageView("path/to/profile/image.png");
            CheckBox favoriteCheckBox = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label profileImageLabel = new Label("Profile Image");
            Label playerNameLabel = new Label(playerName);
            Label winPercentageLabel = new Label(winPercentage);

            leaderboardGridPane.add(profileImageLabel, 0, i);
            leaderboardGridPane.add(playerNameLabel, 1, i);
            leaderboardGridPane.add(winPercentageLabel, 2, i);
            leaderboardGridPane.add(favoriteCheckBox, 3, i);
            leaderboardGridPane.add(profileImage, 1, i+1);
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
}

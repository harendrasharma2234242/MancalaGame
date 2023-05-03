package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavouriteUsersController implements Initializable {
    @FXML
    private GridPane favouriteUsersId;
    @FXML
    private CheckBox favoriteCheckBox;
    @FXML
    private Button back;
    private static String username;
    private static String sessionID;
    private static InputStream profileImageStream;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();

    }
    @FXML
    private void back() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpponentAndGameModeBean.changeScene(event, "OpponentAndGameMode.fxml","Choose!", username, null, null);
            }
        });
    }

    @FXML
    private void refresh() {
        // Here you would implement the logic to retrieve the latest leaderboard data
        // from your backend or data source and update the UI accordingly
        // For the sake of example, let's assume we have some leaderboard data

        // Clear existing rows from the leaderboard grid pane
//        favouriteUsersId.getChildren().removeAll(favouriteUsersId.getChildren());
//
//        favouriteUsersId.add(new Label("Username"), 0, 0);
//        favouriteUsersId.add(new Label("Add/Remove"), 1, 0);
        ArrayList<String> favourites = UsersBean.getAllFavourites(username);

        // Add new rows to the leaderboard grid pane
        for (int i = 0; i < favourites.size(); i++) {
            // Here you would retrieve the player data for the ith player in the leaderboard
            // and use it to populate the UI elements below

            String user = favourites.get(i);
            CheckBox favoriteCheckBox1 = new CheckBox();

            // Add UI elements for the ith player to the leaderboard grid pane
            Label playerNameLabel = new Label(user);

            favouriteUsersId.add(playerNameLabel, 0, i);
            favouriteUsersId.add(favoriteCheckBox1, 1, i);

            favoriteCheckBox1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!favoriteCheckBox1.isSelected()) {
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

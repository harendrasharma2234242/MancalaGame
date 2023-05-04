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

/**
 * Controls the favourite users scene.
 * @author Ikechukwu Ngini
 * @author Alex Wadge
 * @version 2.0
 */
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

    /**
     * Initialise the scene and place data in scene.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    /**
     * Set back button functionality.
     */
    @FXML
    private void back() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpponentAndGameModeBean.changeScene(event, "OpponentAndGameMode.fxml","Choose!", username, null, null);
            }
        });
    }

    /**
     * Update the list of favourite players shown.
     */
    @FXML
    private void refresh() {
        ArrayList<String> favourites = UsersBean.getAllFavourites(username);

        for (int i = 0; i < favourites.size(); i++) {
            String user = favourites.get(i);
            CheckBox favoriteCheckBox1 = new CheckBox();
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

    /**
     * Retrieve the current player's username
     * @param name player's username
     * @param session current session ID
     * @param image player's profile image
     */
    public static void userName(String name, String session, InputStream image) {
        username = name;
        sessionID = session;
        profileImageStream = image;
    }
}

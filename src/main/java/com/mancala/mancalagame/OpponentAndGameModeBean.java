package com.mancala.mancalagame;

import com.mancala.mancalagame.usercontroller.OpponentLogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Functionality relating to the OpponentAndGameMode files.
 * @author Harendra Sharma
 * @version 1.0
 */
public class OpponentAndGameModeBean {
    /**
     * Changes the scene to a new fxml file.
     * @param event action event
     * @param fxmlFile fxml file to move to
     * @param title title of new window
     * @param player1 current player's username
     * @param gameMode current game mode
     * @param loginSession current player's log in session ID
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String player1, String gameMode, String loginSession) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
            root = loader.load();
            if (!fxmlFile.equals("OpponentAndGameMode.fxml")){
                OpponentLogIn opponentLogIn = loader.getController();
                opponentLogIn.setData(player1, gameMode, loginSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}

package com.mancala.mancalagame.opponentcontroller;

import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.OpponentLogInBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpponentAndGameMode implements Initializable {
    @FXML
    private RadioButton traditionalRadio;

    @FXML
    private RadioButton arcadeRadio;

    @FXML
    private RadioButton humanRadio;

    @FXML
    private RadioButton computerRadio;
    @FXML
    private Button secondPlayerLoginButton;
    @FXML
    private Button startButton;
    @FXML
    private Label user1;

    @FXML
    private Label loginSession;

    @FXML
    private void handleGameModeSelection(ActionEvent event) {
        if (event.getSource().equals(traditionalRadio)) {
            arcadeRadio.setSelected(false);
            traditionalRadio.setSelected(true);
        } else if (event.getSource().equals(arcadeRadio)) {
            traditionalRadio.setSelected(false);
            arcadeRadio.setSelected(true);
        }
    }

    @FXML
    private void handleOpponentSelection(ActionEvent event) {
        // If human is selected, load login page
        if (event.getSource().equals(humanRadio)) {
            computerRadio.setSelected(false);
            humanRadio.setSelected(true);
            secondPlayerLoginButton.setVisible(true);
            startButton.setVisible(false);
        } else if (event.getSource().equals(computerRadio)) {
            // Enable start button if a game mode and opponent is selected
            computerRadio.setSelected(true);
            humanRadio.setSelected(false);
            secondPlayerLoginButton.setVisible(false);
            startButton.setVisible(true);

        }
    }
    @FXML
    private void secondPlayerLogin(ActionEvent event){
        if((arcadeRadio.isSelected() || traditionalRadio.isSelected())
                && (computerRadio.isSelected() || humanRadio.isSelected())){
            OpponentAndGameModeBean opoBean = new OpponentAndGameModeBean();
            String player1 = user1.getText();
            String gameMode;
            if(arcadeRadio.isSelected()){
                gameMode = "arcade";
            } else {
                gameMode = "traditional";
            }
            opoBean.changeScene(event,"OpponentLogIn.fxml", "Opponent log in", player1, gameMode, loginSession.getText());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select game mode and user type both");
            alert.show();
        }

    }
    @FXML
    private void handleStartGame() {
        if((arcadeRadio.isSelected() || traditionalRadio.isSelected())
                && (computerRadio.isSelected() || humanRadio.isSelected())){
            System.out.println("Game started!");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select game mode and user type both");
            alert.show();
        }

    }

    @FXML
    public void saveUser1(String user, String sessionId){
        user1.setText(user);
        loginSession.setText(sessionId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String player1 = user1.getText();
                String gameMode;
                if(arcadeRadio.isSelected()){
                    gameMode = "arcade";
                } else {
                    gameMode = "traditional";
                }
                if (gameMode.equals("arcade")){
                    OpponentLogInBean.changeScene(event,"BoardArcade.fxml", "Game Mode", player1, "CPU", gameMode);
                } else {
                    OpponentLogInBean.changeScene(event,"BoardTrad.fxml", "Game Mode", player1, "CPU", gameMode);
                }
            }
        });
    }
}
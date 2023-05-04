package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.OpponentLogInBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class of the screen where you select opponent type and game mode.
 * @author Harendra Sharma
 * @version 1.0
 */
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
    private ImageView userProfileImage;
    @FXML
    private Button myProfile;
    @FXML
    private Button leaderboard;

    /**
     * Functionality of radio buttons to select game mode.
     * @param event The action relating to the component (radio button)
     */
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

    /**
     * Functionality of radio buttons to select what type of opponent is played (CPU/human).
     * @param event The action relating to the component (radio button)
     */
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

    /**
     * Go to opponent login page if all radio buttons are correctly selected.
     * @param event The action relating to the component (radio button)
     */
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

    /**
     * Give alert if not all settings are set.
     */
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

    /**
     * Save the data of the first player.
     * @param user player 1 username
     * @param sessionId ID of the game session
     * @param profileImage player 1 profile image
     */
    @FXML
    public void saveUser1(String user, String sessionId, InputStream profileImage){
        user1.setText(user);
        loginSession.setText(sessionId);
        if (profileImage != null){
            try {
                OutputStream outP = new FileOutputStream(new File("ProfileImage.jpg"));
                byte [] content = new byte[1024];
                int size = 0;
                while ((size = profileImage.read(content)) != -1){
                    outP.write(content, 0,size);
                }
                outP.close();
                profileImage.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Initialise the scene and set start, profile and leaderboard buttons to function.
     * @param url
     * @param resourceBundle
     */
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
                    OpponentLogInBean.changeScene(event,"BoardArcade.fxml", "Game Mode", player1, "CPU", gameMode, loginSession.getText());
                } else {
                    OpponentLogInBean.changeScene(event,"BoardTrad.fxml", "Game Mode", player1, "CPU", gameMode, loginSession.getText());
                }
            }
        });
        myProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = user1.getText();
                UsersBean.changeScene(event, "PlayerProfile.fxml", "Choose", userName, "", null, null);
            }
        });
        leaderboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = user1.getText();
                UsersBean.changeScene(event, "Leaderboard.fxml", "Leaderboard", userName, null, null, null);
            }
        });

    }
}

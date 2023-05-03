package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.OpponentLogInBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * The controller class for the Opponent Log in.
 * @author Harendra Sharma
 * @version 1.0
 */
public class OpponentLogIn implements Initializable {
    @FXML
    private TextField player1;
    @FXML
    private TextField game_mode;
    @FXML
    private TextField tf_username;

    @FXML
    private  TextField tf_password;

    @FXML
    private Button btn_logging;

    @FXML
    private Button btn_back;
    @FXML
    private TextField login_session;
/** setData method is used to set the player, gameMode, and login session information in OpponentLogin fxml page
  */
    public void setData(String player, String gameMode, String loginSession){
        player1.setText(player);
        game_mode.setText(gameMode);
        login_session.setText(loginSession);
    }
    /** This initialize method include two buttons actions
     * 1. Opponent log-in,
     * 2. Back button if player change his mind
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpponentLogInBean.logInUser(event, tf_username.getText(), tf_password.getText(), player1.getText(), game_mode.getText(), login_session.getText());
            }
        });
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpponentAndGameModeBean.changeScene(event, "OpponentAndGameMode.fxml", "Choose options", null, null,null);
            }
        });
    }
}

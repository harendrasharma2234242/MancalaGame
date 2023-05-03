package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class for the Player sign-in, sing-up.
 * @author Harendra Sharma
 * @version 1.0
 */
public class PlayerLogin implements Initializable {
    @FXML
    private Button btnLogging;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnBack;

    /**
     * Log in, signup and back button functionality under initialize method.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.logInUser(event, userName.getText(), password.getText());
            }
        });
        btnSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Sign-up.fxml", "Sign up", null, null, null, null);
            }
        });
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Home.fxml", "Choose", null, null, null, null);
            }
        });
    }
}
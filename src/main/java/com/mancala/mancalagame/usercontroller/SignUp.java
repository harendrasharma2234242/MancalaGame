package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    /**
     * @param url
     * @param resourceBundle
     */

    @FXML
    private Button btn_signup;

    @FXML
    private TextField sp_username;

    @FXML
    private TextField sp_password;

    @FXML
    private Button btn_loggedIn;

    @FXML
    private TextField sp_name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!sp_name.getText().trim().isEmpty() && !sp_username.getText().trim().isEmpty() && !sp_password.getText().trim().isEmpty()) {
                    UsersBean.SignUp(event, sp_name.getText(), sp_username.getText(), sp_password.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to signup!");
                    alert.show();
                }

            }
        });
        btn_loggedIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "PlayerLogIn.fxml", "Log in", null, null);
            }
        });
    }
}

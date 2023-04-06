package com.mancala.mancalagame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
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
                if(!sp_name.getText().trim().isEmpty() && !sp_username.getText().trim().isEmpty() && !sp_password.getText().trim().isEmpty()){
                    DBUtils.SignUp(event,sp_name.getText(), sp_username.getText(), sp_password.getText());
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
                DBUtils.changeScene(event, "home.fxml","Log in", null);
            }
        });
    }
}

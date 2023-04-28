package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminLogIn implements Initializable {
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_logging;

    @FXML
    private TextField ad_username;

    @FXML
    private TextField ad_password;


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.logInAdmin(event, ad_username.getText(), ad_password.getText());
            }
        });
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Home.fxml", "Sign up", null, null);
            }
        });


    }
}

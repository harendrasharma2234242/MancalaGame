package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.AdminBean;
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
 * This AdminLogIn controller is used for admin log in.
 * @author Harendra Sharma
 * @version 1.0
 * */
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

    /** In this initialize method, we have two buttons btn_logging and btn_back,
     * 1. btn_loggin:- Admin log in,
     * 2. btn_back: back to main game screen*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.logInAdmin(event, ad_username.getText(), ad_password.getText());
            }
        });
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.changeScene(event, "Home.fxml", "Choose", null);
            }
        });


    }
}

package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FrequentUsers  implements Initializable {

    @FXML
    private Button back;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "adminDashboard.fxml","Admin Dashboard", null, null, null);
                System.out.println("here");
            }
        });
    }
}

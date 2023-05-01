package com.mancala.mancalagame.admincontroller;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHomeScreen {

    @FXML
    private Button logout;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Home.fxml","Home", null, null, null);
            }
        });
    }
}

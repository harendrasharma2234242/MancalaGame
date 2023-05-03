package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.AdminBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FrequentUsers  implements Initializable {

    @FXML
    private Button back;
    @FXML
    private GridPane frequentUsersId;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<List<String>> frequentUsers = AdminBean.getFrequentUsers();
        addFrequentUsers(frequentUsers);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "adminDashboard.fxml","Admin Dashboard", null, null, null, null);
            }
        });
    }
    public void addFrequentUsers(List<List<String>> frequentUsers){
        for (int i= 0; i < frequentUsers.size(); i++) {
            List<String> users = frequentUsers.get(i);
            RowConstraints row = new RowConstraints();
            row.setMinHeight(10);
            row.setMaxHeight(30);
            frequentUsersId.getRowConstraints().addAll(row);
            Label username = new Label();
            username.setText(users.get(0));
            Label loginCount = new Label();
            loginCount.setText(users.get(1));
            Label lastLogin = new Label();
            lastLogin.setText(users.get(2));

            frequentUsersId.setConstraints(username, 0, i);
            frequentUsersId.setConstraints(loginCount, 2, i);
            frequentUsersId.setConstraints(lastLogin, 1, i);
            frequentUsersId.getChildren().addAll(username, loginCount, lastLogin);
        }
    }
}

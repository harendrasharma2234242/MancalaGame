package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.AdminBean;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewUserRequests implements Initializable {

    @FXML
    private Button back;
    @FXML
    private GridPane newUsers;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminBean.changeScene(event, "adminDashboard.fxml","Admin Dashboard", null, null);
            }
        });

    }
    public void setNewUser(List<List<String>> resultSet){
        for (int i= 0; i < resultSet.size(); i++){
            List<String> users = resultSet.get(i);
            ColumnConstraints column1 = new ColumnConstraints(10, 20,300);
            ColumnConstraints column2 = new ColumnConstraints(300, 20,300);
            newUsers.getColumnConstraints().addAll(column1, column2);
            RowConstraints row = new RowConstraints(30);
            newUsers.getRowConstraints().addAll(row);
            Label username = new Label();
            username.setText(users.get(0));
            CheckBox checkBox = new CheckBox();
            checkBox.setId(users.get(2));
            checkBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            newUsers.setConstraints(username, 1, i);
            newUsers.setConstraints(checkBox, 2, i);
            newUsers.getChildren().addAll(checkBox,username);
        }
    }
    private class MyEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt) {
           int userId = Integer.parseInt(((Control)evt.getSource()).getId());
            AdminBean.updateUsers(userId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User has been activated");
            alert.show();
        }
    }
}

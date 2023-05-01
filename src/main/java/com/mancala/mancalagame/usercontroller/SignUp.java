package com.mancala.mancalagame.usercontroller;

import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    /**
     * @param url
     * @param resourceBundle
     */

    @FXML
    private Button btnSignup;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button btnLoggedIn;

    @FXML
    private TextField name;
    @FXML
    private Button uploadProfileImage;
    private FileChooser fileChooser;
    private File file;
    private FileInputStream fis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InputStream image = null;
                if (fis != null){
                    image = (InputStream) fis;
                }
                if (!name.getText().trim().isEmpty() && !userName.getText().trim().isEmpty() && !password.getText().trim().isEmpty()) {
                    UsersBean.SignUp(event, name.getText(), userName.getText(), password.getText(),image);
                } else {
                    System.out.println("fis Value"+(InputStream)fis + " "+ (int) file.length());
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to signup!");
                    alert.show();
                }

            }
        });
        btnLoggedIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "PlayerLogIn.fxml", "Log in", null, null, null);
            }
        });
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        uploadProfileImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("upload Image");
                stage.show();
                file = fileChooser.showOpenDialog(stage);
                if (file != null){
                    try {
                        fis = new FileInputStream(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }
}

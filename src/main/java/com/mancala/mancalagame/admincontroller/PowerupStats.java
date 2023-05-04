package com.mancala.mancalagame.admincontroller;

import com.mancala.mancalagame.AdminBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the power up statistic page for admins.
 * @author Jamie Lloyd
 * @author Harendra Sharma
 * @version 1.0
 */
public class PowerupStats implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label continueTurnUses;
    @FXML
    private Label doublePointsUses;
    @FXML
    private Label halfHandUses;
    @FXML
    private Label reverseTurnUses;
    @FXML
    private Label switchSidesUses;

    /**
     * Initialise the scene and set text of labels to statistics.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Integer> powerStone =  AdminBean.getAllSpecialStones();
        if (powerStone.size() !=0){
            doublePointsUses.setText(String.valueOf(powerStone.get(0)));
            continueTurnUses.setText(String.valueOf(powerStone.get(1)));
            reverseTurnUses.setText(String.valueOf(powerStone.get(2)));
            switchSidesUses.setText(String.valueOf(powerStone.get(3)));
            halfHandUses.setText(String.valueOf(powerStone.get(4)));
        }

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "adminDashboard.fxml","Admin Dashboard", null,
                        null,null, null);
            }
        });
    }

}

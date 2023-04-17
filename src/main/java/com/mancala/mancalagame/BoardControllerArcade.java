package com.mancala.mancalagame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BoardControllerArcade extends BoardController {
    @FXML
    private Button continueTurn;
    @FXML
    private void onDoublePointsClick() {
        isDouble = true;
        doublePoints.setText("Double points activated");
        doublePoints.setDisable(true);
        reactivateDoublePointsButton(true);
    }
    @FXML
    private void onContinueTurnClick() {

    }
}

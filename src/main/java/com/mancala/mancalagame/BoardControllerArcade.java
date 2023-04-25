package com.mancala.mancalagame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class BoardControllerArcade extends BoardController {
    @FXML
    private Button continueTurn;
    @FXML
    private Button doublePoints;
    @FXML
    private Label specialStone;
    @FXML
    private void onDoublePointsClick() {
        isDoublePoints = true;
        doublePoints.setText("Double points used this turn");
        doublePoints.setDisable(true);
    }
    @FXML
    private void onContinueTurnClick() {
        isContinueTurn = true;
        continueTurn.setText("Continue turn used this turn");
        continueTurn.setDisable(true);
    }
    @FXML
    private int generateSpecialStones() {
        normalSide = true;
        double chance = Math.random();
        int option = -1;
        String[] specialStones = {"half hand", "switch sides", "reverse turn"};
        if (chance <= 1) {
            Random random = new Random();
            option = random.nextInt(3);
            option = 0;
            String stone = specialStones[option];
            specialStone.setText(stone);
            if (option == 1) {
                normalSide = false;
            } else if (option == 2) {
                normalDirection = false;
            } else {
                halfHand = true;
            }
        } else {
            specialStone.setText("");
        }
        return option;
    }
    @FXML
    private void onHole0Click() {
        int stone = generateSpecialStones();
        holeNumber = 0;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole1Click() {
        int stone = generateSpecialStones();
        holeNumber = 1;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole2Click() {
        int stone = generateSpecialStones();
        holeNumber = 2;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole3Click() {
        int stone = generateSpecialStones();
        holeNumber = 3;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole4Click() {
        int stone = generateSpecialStones();
        holeNumber = 4;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole5Click() {
        int stone = generateSpecialStones();
        holeNumber = 5;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole6Click() {
        int stone = generateSpecialStones();
        holeNumber = 6;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole7Click() {
        int stone = generateSpecialStones();
        holeNumber = 7;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole8Click() {
        int stone = generateSpecialStones();
        holeNumber = 8;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole9Click() {
        int stone = generateSpecialStones();
        holeNumber = 9;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole10Click() {
        int stone = generateSpecialStones();
        holeNumber = 10;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }
    @FXML
    private void onHole11Click() {
        int stone = generateSpecialStones();
        holeNumber = 11;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

}

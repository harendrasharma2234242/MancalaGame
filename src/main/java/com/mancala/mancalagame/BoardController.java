package com.mancala.mancalagame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class BoardController {
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label warning;

    @FXML
    private ArrayList<String> holeLabels = new ArrayList<>();
    @FXML
    private ArrayList<String> mancalaLabels = new ArrayList<>();
    @FXML
    private Label holeLabel0;
    @FXML
    private Label holeLabel1;
    @FXML
    private Label holeLabel2;
    @FXML
    private Label holeLabel3;
    @FXML
    private Label holeLabel4;
    @FXML
    private Label holeLabel5;
    @FXML
    private Label holeLabel6;
    @FXML
    private Label holeLabel7;
    @FXML
    private Label holeLabel8;
    @FXML
    private Label holeLabel9;
    @FXML
    private Label holeLabel10;
    @FXML
    private Label holeLabel11;
    @FXML
    private Label mancalaLabel_P1;
    @FXML
    private Label mancalaLabel_P2;
    @FXML
    private ArrayList<Hole> holes = new ArrayList<>();
    @FXML
    private ArrayList<Mancala> mancalas = new ArrayList<>();
    @FXML
    private Board board;
    private int holeNumber;
    private int mancalaNumber;
    private boolean mancalaFilled;
    @FXML
    private void setBoard() {
        for (int i = 0; i <= 12; i++) {
            holes.add(new Hole(4));
            holeLabels.add("4");
        }
        for (int i = 0; i <= 2; i++) {
            mancalas.add(new Mancala(0));
            mancalaLabels.add("0");
        }
        board = new Board(holes, mancalas);
    }
    private void setCurrentPlayer() {
        if (currentPlayer.getText().equals("1")) {
            currentPlayer.setText("2");
        } else {
            currentPlayer.setText("1");
        }
    }

    private boolean checkCurrentPlayer(int player, int hole) {
        if (player == 1 && hole < 6) {
            warning.setText("");
            return true;
        } else if (player == 2 && hole >= 6) {
            warning.setText("");
            return true;
        } else {
            warning.setText("Player 1 must choose from the bottom row, and Player 2 must choose from the top");
            return false;
        }
    }

    @FXML
    private void moveStones(int holeNumber) {
        int chosenHoleCount = holes.get(holeNumber).getCount();
        holes.get(holeNumber).setCount(0);
        holeLabels.set(holeNumber, "0");
        int i = 1;
        int index = i + holeNumber;
        int rightMancalaFlag = 0;
        for (i = 1; i <= chosenHoleCount; i++) {
            if (index == 12) {
                mancalas.get(0).setCount(mancalas.get(0).getCount()+1);
                mancalaLabels.set(0, String.valueOf(mancalas.get(0).getCount()));
                index = 0;
            } else if (index == 6) {
                mancalas.get(1).setCount(mancalas.get(1).getCount()+1);
                mancalaLabels.set(1, String.valueOf(mancalas.get(1).getCount()));
                index++;
                rightMancalaFlag++;
            } else {
                int curr = holes.get(index).getCount();
                holes.get(index-rightMancalaFlag).setCount(curr+1);
                holeLabels.set(index-rightMancalaFlag, String.valueOf(curr+1));
                index++;
            }
        }
        holeLabel0.setText(holeLabels.get(0));
        holeLabel1.setText(holeLabels.get(1));
        holeLabel2.setText(holeLabels.get(2));
        holeLabel3.setText(holeLabels.get(3));
        holeLabel4.setText(holeLabels.get(4));
        holeLabel5.setText(holeLabels.get(5));
        holeLabel6.setText(holeLabels.get(6));
        holeLabel7.setText(holeLabels.get(7));
        holeLabel8.setText(holeLabels.get(8));
        holeLabel9.setText(holeLabels.get(9));
        holeLabel10.setText(holeLabels.get(10));
        holeLabel11.setText(holeLabels.get(11));
        mancalaLabel_P1.setText(mancalaLabels.get(0));
        mancalaLabel_P2.setText(mancalaLabels.get(1));
    }

    @FXML
    private void onHole0Click() {
        holeNumber = 0;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole1Click() {
        holeNumber = 1;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole2Click() {
        holeNumber = 2;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole3Click() {
        holeNumber = 3;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole4Click() {
        holeNumber = 4;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole5Click() {
        holeNumber = 5;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole6Click() {
        holeNumber = 6;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole7Click() {
        holeNumber = 7;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole8Click() {
        holeNumber = 8;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole9Click() {
        holeNumber = 9;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole10Click() {
        holeNumber = 10;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }
    @FXML
    private void onHole11Click() {
        holeNumber = 11;
        if (checkCurrentPlayer(Integer.parseInt(currentPlayer.getText()), holeNumber)) {
            setCurrentPlayer();
            moveStones(holeNumber);
        }
    }

}

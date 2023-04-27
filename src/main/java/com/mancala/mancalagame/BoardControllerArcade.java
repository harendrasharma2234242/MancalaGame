package com.mancala.mancalagame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * The controller class for the arcade version of the game.
 * @author Alex Wadge
 * @version 2.0
 */
public class BoardControllerArcade extends BoardController {
    private static final int MANCALA_1_INDEX_REVERSE = 5;
    @FXML
    private Button continueTurn;
    @FXML
    private Button doublePoints;
    @FXML
    private Label specialStone;
    private boolean isDoublePoints = false;
    private boolean isContinueTurn = false;
    private boolean normalSide = true;
    private boolean normalDirection = true;
    private boolean halfHand = false;
    private boolean newTurn = true;

    /**
     * Swap which side the player collects stones from if the switch sides stone has been generated.
     * @param holeNumber which hole the player chose
     * @param normalSide whether the switch sides stone has been generated
     * @return the new hole number, possibly opposite the original
     */
    private int switchSides(int holeNumber, boolean normalSide) {
        if (!normalSide) {
            holeNumber = LAST_HOLE - holeNumber;
        }
//        System.out.println("change: " + holeNumber);
        return holeNumber;
    }

    /**
     * Fill mancalas when stones go past them, adjusted for whether the double points power-up is active.
     * @param index Which mancala to fill.
     */
    private void fillMancala(int index) {
        if (isDoublePoints) {
            mancalas.get(index).setCount(mancalas.get(index).getCount() + 2);
        } else {
            mancalas.get(index).setCount(mancalas.get(index).getCount() + 1);
        }
        mancalaLabels.set(index, String.valueOf(mancalas.get(index).getCount()));
    }

    /**
     * Reactivates power up button after the turn where it was used ends. Only one use per turn.
     */
    protected void reactivateDoublePointsButton(boolean newTurn) {
        if (newTurn) {
            doublePoints.setDisable(false);
            doublePoints.setText("Double points");
            continueTurn.setDisable(false);
        }
    }

    /**
     * Reactivates power up button after the turn where it was used ends. Only one use per turn.
     */
    protected void reactivateContinueTurnButton(boolean newTurn) {
        if (newTurn) {
            continueTurn.setDisable(false);
            continueTurn.setText("Continue turn");
            doublePoints.setDisable(false);
        }
    }

    /**
     * Links a button on the board to the use of the double points power-up. Allows a player to gain double points for
     * one turn.
     */
    @FXML
    private void onDoublePointsClick() {
        isDoublePoints = true;
        doublePoints.setText("Double points used this turn");
        doublePoints.setDisable(true);
        continueTurn.setDisable(true);
    }

    /**
     * Links a button on the board to the use of the continue turn power-up. Allows a turn to continue when it would
     * normally end.
     */
    @FXML
    private void onContinueTurnClick() {
        isContinueTurn = true;
        continueTurn.setText("Continue turn used this turn");
        continueTurn.setDisable(true);
        doublePoints.setDisable(true);
    }

    /**
     * Update the index of the hole on the board currently being used. Goes in opposite direction if needed.
     * @param index index of initial hole looked at.
     * @param normalDirection whether or not "reverse turn" special stone has been generated.
     */
    private int updateIndex(int index, boolean normalDirection) {
//        System.out.println("before: " + index);
        if (normalDirection) {
            index++;
        } else {
            index--;
        }
//        System.out.println("after: " + index);
        return index;
    }

    /**
     * Generate special stones based on random chance.
     */
    @FXML
    private int generateSpecialStones() {
        normalSide = true;
        normalDirection = true;
        halfHand = false;
        double chance = Math.random();
        int option = -1;
        String[] specialStones = {"half hand", "switch sides", "reverse turn"};
        if (chance <= 0.1) {
            Random random = new Random();
            option = random.nextInt(3);
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

    /**
     * Get the number of stones in a hole, adjusting for whether half-hand stone was picked up.
     * @param holeNumber The hole the player chose to play.
     */
    private int getStones(int holeNumber) {
        float chosenHoleCount;
        if (!halfHand) {
            chosenHoleCount = holes.get(holeNumber).getCount();
            holes.get(holeNumber).setCount(0);
            holeLabels.set(holeNumber, "0");
//            System.out.println("emptying: " + holeNumber + " to " + 0);
        } else {
            chosenHoleCount = Math.round((float) holes.get(holeNumber).getCount() / 2);
            int leftOver = holes.get(holeNumber).getCount() - (int) chosenHoleCount;
            holes.get(holeNumber).setCount(leftOver);
            holeLabels.set(holeNumber, Integer.toString(leftOver));
//            System.out.println("emptying: " + holeNumber + " to " + leftOver);
        }
        return (int) chosenHoleCount;
    }

    /**
     * Play a turn. Redistributes stones based on player's choices and special stone generation.
     * @param holeNumber The hole the player chose to play.
     */
    private void moveStones(int holeNumber) {
        holeNumber = switchSides(holeNumber, normalSide);
        int chosenHoleCount = getStones(holeNumber);
//        newTurn = false;
        int i = 1;
        int index;
        if (normalDirection) {
            index = i + holeNumber;
        } else if (holeNumber != FIRST_HOLE) {
            index = holeNumber - i;
        } else {
            index = 11;
        }
        //flag to adjust which hole is looked at based on whether a mancala has been filled. Stops an offset occurring.
        int rightMancalaFlag = 0;
        //flags to show how the last move ended. To know whether they gained a turn or not.
        boolean normalLastFilled = false;
        boolean rightLastFilled = false;
        boolean leftLastFilled = false;
        // contents of current hole
        int curr = 0;
        int newHoleNumber = 0;
        for (i = 1; i <= chosenHoleCount; i++) {
            if (index == MANCALA_2_INDEX && currentPlayer.getText().equals("2")) {
                //fill player 2's mancala if player 2 passes it
                fillMancala(1);
                index = FIRST_HOLE;
                leftLastFilled = true;
                rightLastFilled = false;
                normalLastFilled = false;
                System.out.println("l fill");
            } else if (index == MANCALA_1_INDEX && currentPlayer.getText().equals("1") && normalDirection) {
                //fill player 1's mancala if player 1 passes it
                fillMancala(0);
                index = updateIndex(index, normalDirection);
                System.out.println("r fill");
                rightMancalaFlag++;
                leftLastFilled = false;
                rightLastFilled = true;
                normalLastFilled = false;
            } else if (index == MANCALA_1_INDEX_REVERSE && currentPlayer.getText().equals("1") && !normalDirection) {
                //fill player 1's mancala if player 1 passes it, but in reverse
                fillMancala(0);
                curr = pickUpStones(index);
                holes.get(index-rightMancalaFlag).setCount(curr + 1);
                holeLabels.set(index-rightMancalaFlag, String.valueOf(curr + 1));
                System.out.println("0. filling: " + (index - rightMancalaFlag) + " to " + (curr + 1));
                index = updateIndex(index, normalDirection);
                leftLastFilled = false;
                rightLastFilled = true;
                normalLastFilled = false;
            } else if (index == MANCALA_2_INDEX) {
                //player 1 skips player 2's mancala
                index = FIRST_HOLE;
                i--;
            } else if (rightMancalaFlag <= index && index != LAST_HOLE) {
                //filling a normal hole
                curr = pickUpStones(index - rightMancalaFlag);
                holes.get(index - rightMancalaFlag).setCount(curr + 1);
                holeLabels.set(index - rightMancalaFlag, String.valueOf(curr + 1));
                System.out.println("1. filling: " + (index - rightMancalaFlag) + " to " + (curr + 1));
                newHoleNumber = index - rightMancalaFlag;
                index = updateIndex(index, normalDirection);
                leftLastFilled = false;
                rightLastFilled = false;
                normalLastFilled = true;
            } else {
                //filling the last hole
                curr = pickUpStones(LAST_HOLE);
                holes.get(LAST_HOLE).setCount(curr + 1);
                holeLabels.set(LAST_HOLE, String.valueOf(curr + 1));
                newHoleNumber = LAST_HOLE;
                index = updateIndex(index, normalDirection);
                leftLastFilled = false;
                rightLastFilled = false;
                normalLastFilled = true;
                System.out.println("2. filling: " + LAST_HOLE + " to " + (curr + 1));

            }
        }

        setLabels();
        board.printBoard();
        System.out.println();

        if (rightLastFilled || leftLastFilled) {
            notification.setText("Take another turn!");
            normalSide = true;
//            newTurn = true;
        } else if (normalLastFilled && curr != 0) {
            notification.setText("Ended in a non-empty hole - turn continues");
            normalSide = true;
            moveStones(newHoleNumber);
        } else if (isContinueTurn) {
            notification.setText("Power up! Take another turn");
            continueTurn.setDisable(true);
            normalSide = true;
        } else {
            setCurrentPlayer();
            notification.setText("");
            System.out.println("**************next player");
            normalSide = true;
            newTurn = true;
        }
        reactivateDoublePointsButton(newTurn);
        reactivateContinueTurnButton(newTurn);
        isDoublePoints = false;
        isContinueTurn = false;
        gameEnd();
    }

    /**
     * Set the current player and reset power-up buttons for new turn.
     */
    private void setCurrentPlayer() {
        newTurn = true;
        reactivateDoublePointsButton(newTurn);
        reactivateContinueTurnButton(newTurn);
        if (currentPlayer.getText().equals("1")) {
            currentPlayer.setText("2");
            button0.setDisable(true);
            button1.setDisable(true);
            button2.setDisable(true);
            button3.setDisable(true);
            button4.setDisable(true);
            button5.setDisable(true);
            button6.setDisable(false);
            button7.setDisable(false);
            button8.setDisable(false);
            button9.setDisable(false);
            button10.setDisable(false);
            button11.setDisable(false);

        } else {
            currentPlayer.setText("1");
            button0.setDisable(false);
            button1.setDisable(false);
            button2.setDisable(false);
            button3.setDisable(false);
            button4.setDisable(false);
            button5.setDisable(false);
            button6.setDisable(true);
            button7.setDisable(true);
            button8.setDisable(true);
            button9.setDisable(true);
            button10.setDisable(true);
            button11.setDisable(true);
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 0. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 1. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 2. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 3. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 4. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 5. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 6. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 7. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 8. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 9. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 10. Initialises special stone
     * generation.
     */
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

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 11. Initialises special stone
     * generation.
     */
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

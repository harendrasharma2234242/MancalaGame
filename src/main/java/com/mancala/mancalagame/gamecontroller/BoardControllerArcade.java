package com.mancala.mancalagame.gamecontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
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
    @FXML
    private Label cpuPowerUp;
    private boolean cpuPowerUpUsed = false;
    private ArrayList<Integer> playedHolesP1 = new ArrayList<>();
    private ArrayList<Integer> playedHolesP2 = new ArrayList<>();


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
        mancalaGameBean.updateSpecialCases("doublePoint", gameSessionId);
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
        mancalaGameBean.updateSpecialCases("continueTurn", gameSessionId);
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
        if (normalDirection) {
            index++;
        } else {
            index--;
        }
        return index;
    }

    /**
     * Generate special stones based on random chance.
     * @return an ID for the stone generated, or -1 if none
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
            mancalaGameBean.updateSpecialCases(stone, gameSessionId);
            specialStone.setText(currentPlayer.getText() + " picked up a " + stone + " stone!");
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
     * Set the board ready to be played. Extends traditional version. Enables/disables buttons and fills holes.
     */
    @FXML
    private void setBoardArcade() {
        setBoard();
        continueTurn.setDisable(false);
        doublePoints.setDisable(false);
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
        } else {
            chosenHoleCount = Math.round((float) holes.get(holeNumber).getCount() / 2);
            int leftOver = holes.get(holeNumber).getCount() - (int) chosenHoleCount;
            holes.get(holeNumber).setCount(leftOver);
            holeLabels.set(holeNumber, Integer.toString(leftOver));
        }
        return (int) chosenHoleCount;
    }

    /**
     * Play a turn. Redistributes stones based on player's choices and special stone generation.
     * @param holeNumber The hole the player chose to play.
     */
    @FXML
    private void moveStones(int holeNumber) {
        holeNumber = switchSides(holeNumber, normalSide);
        int chosenHoleCount = getStones(holeNumber);
        int i = 1;
        int index;
        if (normalDirection) {
            index = i + holeNumber;
        } else if (holeNumber != FIRST_HOLE) {
            index = holeNumber - i;
        } else {
            index = 11;
        }
        if (currentPlayer.getText().equals(player1)) {
            playedHolesP1.add(index - 1);
        } else {
            playedHolesP2.add(index - 1);
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
            if (index == MANCALA_2_INDEX && currentPlayer.getText().equals(opponentPlayer)) {
                //fill player 2's mancala if player 2 passes it
                fillMancala(1);
                index = FIRST_HOLE;
                leftLastFilled = true;
                rightLastFilled = false;
                normalLastFilled = false;
            } else if (index == MANCALA_1_INDEX && currentPlayer.getText().equals(player1) && normalDirection) {
                //fill player 1's mancala if player 1 passes it
                fillMancala(0);
                index = updateIndex(index, normalDirection);
                rightMancalaFlag++;
                leftLastFilled = false;
                rightLastFilled = true;
                normalLastFilled = false;
            } else if (index == MANCALA_1_INDEX_REVERSE && currentPlayer.getText().equals(player1) && !normalDirection) {
                //fill player 1's mancala if player 1 passes it, but in reverse
                fillMancala(0);
                index = updateIndex(index, normalDirection);
                rightMancalaFlag++;
                leftLastFilled = false;
                rightLastFilled = true;
                normalLastFilled = false;
            } else if (index == MANCALA_2_INDEX) {
                //player 1 skips player 2's mancala
                index = FIRST_HOLE;
                i--;
            } else if (rightMancalaFlag <= index) {
                //filling a normal hole
                curr = getCount(index - rightMancalaFlag);
                holes.get(index - rightMancalaFlag).setCount(curr + 1);
                holeLabels.set(index - rightMancalaFlag, String.valueOf(curr + 1));
                newHoleNumber = index - rightMancalaFlag;
                index = updateIndex(index, normalDirection);
                leftLastFilled = false;
                rightLastFilled = false;
                normalLastFilled = true;
            } else {
                //filling the last hole
                curr = getCount(LAST_HOLE);
                holes.get(LAST_HOLE).setCount(curr + 1);
                holeLabels.set(LAST_HOLE, String.valueOf(curr + 1));
                newHoleNumber = LAST_HOLE;
                index = updateIndex(index, normalDirection);
                leftLastFilled = false;
                rightLastFilled = false;
                normalLastFilled = true;

            }
        }

        setLabels();

        if (rightLastFilled || leftLastFilled) {
            notification.setText("Take another turn!");
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            normalSide = true;
            newTurn = true;
            if (currentPlayer.getText().equals("CPU")) {
                computerTurn(opponentPlayer);
            }
        } else if (normalLastFilled && curr != 0) {
            normalSide = true;
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            moveStones(newHoleNumber);
        } else if (isContinueTurn) {
            notification.setText("Power up! Take another turn");
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            continueTurn.setDisable(true);
            normalSide = true;
            newTurn = false;
            reactivateDoublePointsButton(newTurn);
            reactivateContinueTurnButton(newTurn);
            isDoublePoints = false;
            isContinueTurn = false;
            if (currentPlayer.getText().equals("CPU")) {
                computerTurn(opponentPlayer);
            }
        } else {
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            if (currentPlayer.getText().equals(player1)) {
                playedHolesP2 = new ArrayList<Integer>();
            } else {
                playedHolesP1 = new ArrayList<Integer>();
            }
            setCurrentPlayer();
            notification.setText("");
            normalSide = true;
            newTurn = true;
            reactivateDoublePointsButton(newTurn);
            reactivateContinueTurnButton(newTurn);
            isDoublePoints = false;
            isContinueTurn = false;
        }
        gameEnd(player1, opponentPlayer);
    }

    /**
     * Set the current player and reset power-up buttons for new turn.
     */
    private void setCurrentPlayer() {
        newTurn = true;
        reactivateDoublePointsButton(newTurn);
        reactivateContinueTurnButton(newTurn);
        if (currentPlayer.getText().equals(player1)) {
            currentPlayer.setText(opponentPlayer);
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
            cpuPowerUp.setText("");


        } else {
            currentPlayer.setText(player1);
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
            cpuPowerUpUsed = false;
        }
        computerTurn(opponentPlayer);
    }

    /**
     * Starts a computer's turn if the current player is CPU. Allows CPU to use power ups via computerPowerUp().
     * @param opponentPlayer the second player in the current game.
     */
    private void computerTurn(String opponentPlayer) {
        if (opponentPlayer.equals("CPU") && currentPlayer.getText().equals(opponentPlayer)) {
            int computerChoice = computerChoice();
            if (!cpuPowerUpUsed) {
                computerPowerUp();
            }
            moveStones(computerChoice);
            if (cpuSecondTurn) {
                cpuSecondTurn = false;
                setCurrentPlayer();
            }
        }
        cpuSecondTurn = false;
    }

    /**
     * Allows the computer to use continue turn and double points power ups. 20% chance of using either.
     */
    private void computerPowerUp() {
        double chance = Math.random();
        if (chance <= 0.2) {
            Random random = new Random();
            int option = random.nextInt(2);
            if (option == 0) {
                cpuPowerUp.setText("CPU used double points");
                onDoublePointsClick();
            } else {
                cpuPowerUp.setText("CPU used continue turn");
                onContinueTurnClick();
            }
            cpuPowerUpUsed = true;
        } else {
            cpuPowerUpUsed = false;
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 0. Initialises special stone
     * generation.
     */
    @FXML
    private void onHole0ClickArcade() {
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
    private void onHole1ClickArcade() {
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
    private void onHole2ClickArcade() {
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
    private void onHole3ClickArcade() {
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
    private void onHole4ClickArcade() {
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
    private void onHole5ClickArcade() {
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
    private void onHole6ClickArcade() {
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
    private void onHole7ClickArcade() {
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
    private void onHole8ClickArcade() {
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
    private void onHole9ClickArcade() {
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
    private void onHole10ClickArcade() {
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
    private void onHole11ClickArcade() {
        int stone = generateSpecialStones();
        holeNumber = 11;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Initialises the game session and sets the strings of each player name
     * @param player1 main player's username
     * @param player2 opponent's username
     * @param loginSession ID for the login session of player1
     */
    public void setPlayer(String player1, String player2, String loginSession){
        this.player1 = player1;
        this.opponentPlayer = player2;
        this.currentPlayer.setText(player1);
        this.gameSessionId = loginSession;
        mancalaGameBean.initiateGame(player1, player2, "Arcade", loginSession);
    }
}

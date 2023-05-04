package com.mancala.mancalagame.gamecontroller;

import com.mancala.mancalagame.MancalaGameBean;
import com.mancala.mancalagame.OpponentAndGameModeBean;
import com.mancala.mancalagame.UsersBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The controller class for the traditional game.
 * @author Alex Wadge
 * @version 2.0
 */
public class BoardController {
    protected static final int MANCALA_1_INDEX = 6;
    protected static final int MANCALA_2_INDEX = 12;
    protected static final int LAST_HOLE = 11;
    protected static final int FIRST_HOLE = 0;
    @FXML
    protected final ArrayList<Hole> holes = new ArrayList<>();
    private final ArrayList<Hole> holesPlayer1 = new ArrayList<>();
    private final ArrayList<Hole> holesPlayer2 = new ArrayList<>();
    @FXML
    protected final ArrayList<Mancala> mancalas = new ArrayList<>();
    protected int holeNumber;
    @FXML
    protected Button button0;
    @FXML
    protected Button button1;
    @FXML
    protected Button button2;
    @FXML
    protected Button button3;
    @FXML
    protected Button button4;
    @FXML
    protected Button button5;
    @FXML
    protected Button button6;
    @FXML
    protected Button button7;
    @FXML
    protected Button button8;
    @FXML
    protected Button button9;
    @FXML
    protected Button button10;
    @FXML
    protected Button button11;
    @FXML
    protected Label currentPlayer;
    @FXML
    protected Label notification;
    @FXML
    protected Button back;

    @FXML
    protected ArrayList<String> holeLabels = new ArrayList<>();
    @FXML
    protected ArrayList<String> mancalaLabels = new ArrayList<>();

    @FXML
    private Button start;
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
    private TextField stoneCountEntry;
    protected String player1;
    protected String opponentPlayer;
    protected String gameSessionId;
    MancalaGameBean mancalaGameBean = new MancalaGameBean();
    private ArrayList<Integer> playedHolesP1 = new ArrayList<>();
    private ArrayList<Integer> playedHolesP2 = new ArrayList<>();
    @FXML
    protected Label played1;
    @FXML
    protected Label played2;
    @FXML
    protected Label instructions;

    protected boolean cpuSecondTurn = false;
    @FXML
    protected Label winnerLabel;
    @FXML
    protected Rectangle winnerBackground;

    /**
     * Button functionality to exit the game.
     */
    @FXML
    protected void back() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersBean.changeScene(event, "Home.fxml","Home", null, null, null, null);

            }
        });
    }
    /**
     * Initialise the game board with stones in holes and buttons enabled/disabled.
     */
    @FXML
    protected void setBoard() {
        mancalaGameBean.updateGameStatus("Inprogress", gameSessionId);
        int stoneCount;
        try {
            stoneCount = Integer.parseInt(stoneCountEntry.getText());
        } catch (Exception e) {
            stoneCount = 4;
            instructions.setText("No number given - default 4 used");
        }
        start.setDisable(true);
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
        for (int i = 0; i < 12; i++) {
            holes.add(new Hole(stoneCount));
            holeLabels.add(String.valueOf(stoneCount));
        }
        for (int i = 0; i <= 2; i++) {
            mancalas.add(new Mancala(0));
            mancalaLabels.add("0");
        }
        for (int i = 0; i < 6; i++) {
            holesPlayer1.add(holes.get(i));
            holesPlayer2.add(holes.get(i+6));
        }
        holeLabel0.setText(Integer.toString(stoneCount));
        holeLabel1.setText(Integer.toString(stoneCount));
        holeLabel2.setText(Integer.toString(stoneCount));
        holeLabel3.setText(Integer.toString(stoneCount));
        holeLabel4.setText(Integer.toString(stoneCount));
        holeLabel5.setText(Integer.toString(stoneCount));
        holeLabel6.setText(Integer.toString(stoneCount));
        holeLabel7.setText(Integer.toString(stoneCount));
        holeLabel8.setText(Integer.toString(stoneCount));
        holeLabel9.setText(Integer.toString(stoneCount));
        holeLabel10.setText(Integer.toString(stoneCount));
        holeLabel11.setText(Integer.toString(stoneCount));

        stoneCountEntry.setVisible(false);
        instructions.setVisible(false);
    }

    /**
     * Set the current player and check if computer needs a turn.
     */
    private void setCurrentPlayer() {
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
        }
        computerTurn(opponentPlayer);
    }

    /**
     * Starts a computer's turn if the current player is CPU.
     * @param opponentPlayer the second player in the current game.
     */
    private void computerTurn(String opponentPlayer) {
        if (opponentPlayer.equals("CPU") && currentPlayer.getText().equals(opponentPlayer)) {
            int computerChoice = computerChoice();
            moveStones(computerChoice);
            if (cpuSecondTurn) {
                cpuSecondTurn = false;
                setCurrentPlayer();
            }
        }
        cpuSecondTurn = false;
    }

    /**
     * Computer's choice of hole to play.
     * @return the hole the computer chose to play.
     */
    protected int computerChoice() {
        ArrayList<Integer> notEmpty = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (checkEmpty(i+6)) {
                notEmpty.add(i+6);
            }
        }
       if (notEmpty.size() != 0) {
           Random random = new Random();
           int option = random.nextInt(notEmpty.size());
           return notEmpty.get(option);
       } else {
           getWinner(player1, opponentPlayer);
           return 0;
       }
    }

    /**
     * Set the labels displayed on the game board.
     */
    protected void setLabels() {
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

    /**
     * Fill mancalas when stones go past them.
     * @param index Which mancala to fill.
     */
    private void fillMancala(int index) {
        mancalas.get(index).setCount(mancalas.get(index).getCount() + 1);
        mancalaLabels.set(index, String.valueOf(mancalas.get(index).getCount()));
    }

    /**
     * Pick up the stones in a given hole.
     * @param index Which hole to collect stones from.
     * @return The number of stones in a hole.
     */
    protected int getCount(int index) {
        int curr;
        curr = holes.get(index).getCount();
        return curr;
    }

    /**
     * Play a turn. Redistribute stones based on player's choices.
     * @param holeNumber The hole the player chose to play.
     */
    @FXML
    private void moveStones(int holeNumber) {
        int chosenHoleCount = holes.get(holeNumber).getCount();
        holes.get(holeNumber).setCount(0);
        holeLabels.set(holeNumber, "0");
        int i = 1;
        int index = i + holeNumber;
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
            } else if (index == MANCALA_1_INDEX && currentPlayer.getText().equals(player1)) {
                //fill player 1's mancala if player 1 passes it
                fillMancala(0);
                index++;
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
                index++;
                leftLastFilled = false;
                rightLastFilled = false;
                normalLastFilled = true;
            } else {
                //filling the last hole
                curr = getCount(LAST_HOLE);
                holes.get(LAST_HOLE).setCount(curr + 1);
                holeLabels.set(LAST_HOLE, String.valueOf(curr + 1));
                newHoleNumber = LAST_HOLE;
                index++;
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
            if (currentPlayer.getText().equals("CPU")) {
                cpuSecondTurn = true;
                computerTurn(opponentPlayer);
            }
        } else if (normalLastFilled && curr != 0) {
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            moveStones(newHoleNumber);
        } else {
            played1.setText(player1 + " played: " + playedHolesP1.toString() + " last turn");
            played2.setText(opponentPlayer + " played: " + playedHolesP2.toString() + " last turn");
            if (currentPlayer.getText().equals(player1)) {
                playedHolesP2 = new ArrayList<Integer>();
            } else {
                playedHolesP1 = new ArrayList<Integer>();
            }
            notification.setText("");
            setCurrentPlayer();
        }
        gameEnd(player1, opponentPlayer);

    }

    /**
     * Check whether the game has ended.
     * @param mainPlayer player 1 for the game
     * @param opponentPlayer player 2 for the game
     */
    protected void gameEnd(String mainPlayer, String opponentPlayer) {
        if (totalContents(holesPlayer1) == 0) {
            mancalas.get(1).setCount(mancalas.get(1).getCount() + totalContents(holesPlayer2));
            mancalaLabels.set(1, String.valueOf(mancalas.get(1).getCount()));
            for (Hole h : holes) {
                h.setCount(0);
            }
            Collections.fill(holeLabels, "0");
            setLabels();
            getWinner(mainPlayer, opponentPlayer);
        } else if (totalContents(holesPlayer2) == 0) {
            mancalas.get(0).setCount(mancalas.get(0).getCount() + totalContents(holesPlayer1));
            mancalaLabels.set(0, String.valueOf(mancalas.get(0).getCount()));
            for (Hole h : holes) {
                h.setCount(0);
            }
            Collections.fill(holeLabels, "0");
            setLabels();
            getWinner(mainPlayer, opponentPlayer);
        }
    }

    /**
     * Return which player won the game if the game ended.
     * @param mainPlayer player 1 for the game
     * @param opponentPlayer player 2 for the game
     * @return Which player won
     */
    private String getWinner(String mainPlayer, String opponentPlayer) {
        int score1 = mancalas.get(0).getCount();
        int score2 = mancalas.get(1).getCount();
        String winner;
        if (score1 < score2) {
            notification.setText(opponentPlayer + " wins!");
            winner = opponentPlayer;
        } else if (score1 > score2) {
            notification.setText(mainPlayer + " wins!");
            winner = mainPlayer;
        } else {
            notification.setText("It's a draw!");
            winner = "NA";
        }
        mancalaGameBean.declareWinner("Finish", winner, gameSessionId);
        button0.setDisable(true);
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
        button5.setDisable(true);
        button6.setDisable(true);
        button7.setDisable(true);
        button8.setDisable(true);
        button9.setDisable(true);
        button10.setDisable(true);
        button11.setDisable(true);
        winnerLabel.setVisible(true);
        winnerLabel.setText(winner + " won! \n" + score1 + " : " + score2);
        winnerBackground.setVisible(true);
        back.setLayoutX(305);
        back.setLayoutY(88);
        return winner;
    }

    /**
     * Return the number of stones in a list of holes. Used to check if a player's side of the board is empty.
     * @param holes ArrayList of holes to check the contents of.
     * @return The sum of the contents of the holes given.
     */
    private int totalContents(ArrayList<Hole> holes) {
        int total = 0;
        for (Hole h : holes) {
            total += h.getCount();
        }
        return total;
    }

    /**
     * Check if a specific hole is empty.
     * @param holeNumber Which hole to check.
     * @return boolean whether the hole was empty
     */
    protected boolean checkEmpty(int holeNumber) {
        return holes.get(holeNumber).getCount() != 0;
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 0.
     */
    @FXML
    private void onHole0Click() {
        holeNumber = 0;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 1.
     */
    @FXML
    private void onHole1Click() {
        holeNumber = 1;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 2.
     */
    @FXML
    private void onHole2Click() {
        holeNumber = 2;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 3.
     */
    @FXML
    private void onHole3Click() {
        holeNumber = 3;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 4.
     */
    @FXML
    private void onHole4Click() {
        holeNumber = 4;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 5.
     */
    @FXML
    private void onHole5Click() {
        holeNumber = 5;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 6.
     */
    @FXML
    private void onHole6Click() {
        holeNumber = 6;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 7.
     */
    @FXML
    private void onHole7Click() {
        holeNumber = 7;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 8.
     */
    @FXML
    private void onHole8Click() {
        holeNumber = 8;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 9.
     */
    @FXML
    private void onHole9Click() {
        holeNumber = 9;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 10.
     */
    @FXML
    private void onHole10Click() {
        holeNumber = 10;
        if (checkEmpty(holeNumber)) {
            moveStones(holeNumber);
        } else {
            notification.setText("Choose a non-empty hole");
        }
    }

    /**
     * Set the hole that is chosen, connected with a button press. Starts a turn from hole 11.
     */
    @FXML
    private void onHole11Click() {
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
        mancalaGameBean.initiateGame(player1, player2, "Traditional", loginSession);
    }

}
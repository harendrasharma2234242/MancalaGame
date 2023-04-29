package com.mancala.mancalagame.query;

/**This Game query class is used to store all the queries related to game
 * @author Harendra Sharma
 * @version 1.0
 * */
public class GameQuery {
    private static final String GAME_INITIATE = "INSERT INTO gamerecord (player1, opponentPlayer, gameMode, gameSessionId) VALUES (?, ?, ?, ?)";
    private static final String START_GAME = "UPDATE gamerecord SET gameStatus = ? WHERE gameSessionId = ?";
    private static final String DECLARE_WINNER = "UPDATE gamerecord SET gameStatus = ?, winner = ? WHERE gameSessionId = ?";
    private static final String DOUBLE_POINT = "UPDATE gamerecord SET doublePoint = ? WHERE gameSessionId = ?";
    private static final String CONTINUE_TURN = "UPDATE gamerecord SET continueTurn = ? WHERE gameSessionId = ?";
    private static final String REVERSE_TURN = "UPDATE gamerecord SET reverseTurn = ? WHERE gameSessionId = ?";
    private static final String HALF_HAND = "UPDATE gamerecord SET halfHand = ? WHERE gameSessionId = ?";
    private static final String SWITCH_SIDE = "UPDATE gamerecord SET switchSide = ? WHERE gameSessionId = ?";
    private static final String GET_GAME = "SELECT * FROM gamerecord where gameSessionId = ?";

    public String gameInitiate(){return GAME_INITIATE;}
    public String startGame(){return START_GAME;}
    public String declareWinner(){return DECLARE_WINNER;}
    public String updateDoublePoint(){return DOUBLE_POINT;}
    public String updateContinueTurn(){return CONTINUE_TURN;}

    public static String updateReverseTurn() {return REVERSE_TURN;}
    public static String updateHalfHand() {return HALF_HAND;}
    public static String updateSwitchSide() {return SWITCH_SIDE;}


    public String getGame(){return GET_GAME;}
}

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
    /**
     * Get query to add new game record.
     * @return query to add new game record.
     */
    public String gameInitiate(){return GAME_INITIATE;}
    /**
     * Get query to update game status.
     * @return query to update game status.
     */
    public String startGame(){return START_GAME;}
    /**
     * Get query to declare winner.
     * @return query to declare winner.
     */
    public String declareWinner(){return DECLARE_WINNER;}
    /**
     * Get query to see how many double points used.
     * @return query to see how many double points used.
     */
    public String updateDoublePoint(){return DOUBLE_POINT;}
    /**
     * Get query to see how many continue turns used.
     * @return query to see how many continue turns used.
     */
    public String updateContinueTurn(){return CONTINUE_TURN;}
    /**
     * Get query to see how many reverse turns used.
     * @return query to see how many reverse turns used.
     */
    public static String updateReverseTurn() {return REVERSE_TURN;}
    /**
     * Get query to see how many half hands used.
     * @return query to see how many half hands used.
     */
    public static String updateHalfHand() {return HALF_HAND;}
    /**
     * Get query to see how many switch sides used.
     * @return query to see how many switch sides used.
     */
    public static String updateSwitchSide() {return SWITCH_SIDE;}
    /**
     * Get query to return a game session id
     * @return query to return a game session id.
     */
    public String getGame(){return GET_GAME;}
}

package com.mancala.mancalagame;

import com.mancala.mancalagame.query.GameQuery;
import com.mancala.mancalagame.utility.DBConnection;

import java.sql.*;

/**This is Mancala game bean class for save all game related information in database
 * @author Harendra Sharma
 * @version 1.0
 * */
public class MancalaGameBean {
    static DBConnection dbConnection = new DBConnection();
    private static final String DBURL = dbConnection.getDBURL();
    private static final String DBNAME = dbConnection.getDBNAME();
    private static final String PASS = dbConnection.getPASS();
    Connection connection = null;

    /**InitiateGame method id used for insert the game related all information*/
    public void initiateGame(String player1, String opponentPlayer, String gameMode, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            String gameInitiateQuery = gameQuery.gameInitiate();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(gameInitiateQuery);
            preparedStatement.setString(1, player1);
            preparedStatement.setString(2, opponentPlayer);
            preparedStatement.setString(3, gameMode);
            preparedStatement.setString(4, gameSessionId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**updateGameStatus method id used for update game status when game is start OR draw*/
    public void  updateGameStatus(String gameStatus, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            String startGameQuery = gameQuery.startGame();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(startGameQuery);
            preparedStatement.setString(1, gameStatus);
            preparedStatement.setString(2, gameSessionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**declareWinner method is used for update game winner and status when game is finished*/
    public void declareWinner(String gameStatus, String username, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            String winnerQuery = gameQuery.declareWinner();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(winnerQuery);
            preparedStatement.setString(1, gameStatus);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, gameSessionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**updateSpecialPoints method is used for update game special points like continue turn or double stone*/
    public void updateSpecialPoints(String specialPointName, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            ResultSet resultSet = null;
            String updateDoublePointQuery = gameQuery.updateDoublePoint();
            String updateContinueTurnQuery = gameQuery.updateContinueTurn();
            String getData = gameQuery.getGame();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement gameStatement = connection.prepareStatement(getData);
            gameStatement.setString(1, gameSessionId);
            resultSet = gameStatement.executeQuery();
            if (resultSet.isBeforeFirst()){
                if (specialPointName.equals("continueTurn")){
                    PreparedStatement continueStatement = connection.prepareStatement(updateContinueTurnQuery);
                    int continueTurn = resultSet.getInt("continueTurn");
                    continueStatement.setInt(1, continueTurn+1);
                    continueStatement.setString(2, gameSessionId);
                    continueStatement.executeUpdate();
                } else if (specialPointName.equals("doubleStone")) {
                    PreparedStatement doubleStatement = connection.prepareStatement(updateDoublePointQuery);
                    int doubleTurn = resultSet.getInt("doublePoint");
                    doubleStatement.setInt(1, doubleTurn+1);
                    doubleStatement.setString(2, gameSessionId);
                    doubleStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

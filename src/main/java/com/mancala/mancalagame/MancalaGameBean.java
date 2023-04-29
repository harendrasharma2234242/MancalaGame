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
    /**updateGameStatus method is used for update game status when game is start OR draw*/
    public void  updateGameStatus(String gameStatus, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            String startGameQuery = gameQuery.startGame();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(startGameQuery);
            preparedStatement.setString(1, gameStatus);
            preparedStatement.setString(2, gameSessionId);
            System.out.println(preparedStatement);
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

    /**updateSpecialPoints method is used for update game special points like continue turn or double stone
     * @param gameSessionId the ID of the game being played
     * @param specialPointName which special stone is used
     * */
    public void updateSpecialCases(String specialPointName, String gameSessionId){
        try {
            GameQuery gameQuery = new GameQuery();
            ResultSet resultSet = null;
            String updateDoublePointQuery = gameQuery.updateDoublePoint();
            String updateContinueTurnQuery = gameQuery.updateContinueTurn();
            String updateReverseTurnQuery = gameQuery.updateReverseTurn();
            String updateHalfHandQuery = gameQuery.updateHalfHand();
            String updateSwitchSideQuery = gameQuery.updateSwitchSide();
            String getData = gameQuery.getGame();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement gameStatement = connection.prepareStatement(getData);
            gameStatement.setString(1, gameSessionId);
            resultSet = gameStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    if (specialPointName.equals("continueTurn")) {
                        PreparedStatement continueStatement = connection.prepareStatement(updateContinueTurnQuery);
                        int continueTurn = resultSet.getInt("continueTurn");
                        continueStatement.setInt(1, continueTurn + 1);
                        continueStatement.setString(2, gameSessionId);
                        continueStatement.executeUpdate();
                    } else if (specialPointName.equals("doublePoint")) {
                        PreparedStatement doubleStatement = connection.prepareStatement(updateDoublePointQuery);
                        int doubleTurn = resultSet.getInt("doublePoint");
                        doubleStatement.setInt(1, doubleTurn + 1);
                        doubleStatement.setString(2, gameSessionId);
                        doubleStatement.executeUpdate();
                    } else if (specialPointName.equals("reverse turn")) {
                        PreparedStatement reverseStatement = connection.prepareStatement(updateReverseTurnQuery);
                        int reverseTurn = resultSet.getInt("reverseTurn");
                        reverseStatement.setInt(1, reverseTurn + 1);
                        reverseStatement.setString(2, gameSessionId);
                        reverseStatement.executeUpdate();
                    } else if (specialPointName.equals("switch sides")) {
                        PreparedStatement switchSideStatement = connection.prepareStatement(updateSwitchSideQuery);
                        int switchSide = resultSet.getInt("switchSide");
                        switchSideStatement.setInt(1, switchSide + 1);
                        switchSideStatement.setString(2, gameSessionId);
                        switchSideStatement.executeUpdate();
                    } else if (specialPointName.equals("half hand")) {
                        PreparedStatement halfHandStatement = connection.prepareStatement(updateHalfHandQuery);
                        int halfHand = resultSet.getInt("halfHand");
                        halfHandStatement.setInt(1, halfHand + 1);
                        halfHandStatement.setString(2, gameSessionId);
                        halfHandStatement.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

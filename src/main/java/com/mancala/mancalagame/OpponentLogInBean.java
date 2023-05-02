package com.mancala.mancalagame;

import com.mancala.mancalagame.gamecontroller.BoardController;
import com.mancala.mancalagame.gamecontroller.BoardControllerArcade;
import com.mancala.mancalagame.opponentcontroller.OpponentAndGameMode;
import com.mancala.mancalagame.query.UsersQuery;
import com.mancala.mancalagame.utility.DBConnection;
import com.mancala.mancalagame.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**This is user bean class for opponent user log in
 * @author Harendra Sharma
 * @version 1.0
 * */
public class OpponentLogInBean {
    static UsersQuery queryUtils = new UsersQuery();
    static DBConnection dbConnection = new DBConnection();
    private static final String DBURL = dbConnection.getDBURL();
    private static final String DBNAME = dbConnection.getDBNAME();
    private static final String PASS = dbConnection.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String player1, String player2, String gameMode, String loginSession) {
        Parent root = null;
        if (player1 != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                root = loader.load();
                System.out.println("here");
                if (gameMode.equals("arcade")){
                    BoardControllerArcade game = loader.getController();
                    game.setPlayer(player1, player2, loginSession);
                } else {
                    System.out.println("here1");
                    BoardController traditional = loader.getController();
                    System.out.println("change scene: ");
                    traditional.setPlayer(player1, player2, loginSession);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(UsersBean.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 750, 500));
        stage.show();
    }
    /** This logInUser method is used for opponent log in and will route user to game mode based on the choice*/
    public static void logInUser(ActionEvent event, String username, String password, String player1, String gameMode, String loginSession) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            final String LOGIN_QUERY = queryUtils.getLOGIN_QUERY();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Player not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            } else {
                while (resultSet.next()) {
                    if (resultSet.getBoolean("is_Active") == false) {
                        System.out.println("Player is not activated yet!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Player is not activated yet!");
                        alert.show();
                    } else if (resultSet.getString("password").equals(password)) {
                        Utility utility = new Utility();
                        String sessionId = utility.getRandomKey().toString();
                        InputStream profileImage = resultSet.getBinaryStream("profileImage");
                        final String SET_SESSION = queryUtils.getSaveSession();
                        preparedStatement = connection.prepareStatement(SET_SESSION);
                        preparedStatement.setString(1, sessionId);
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                        if (gameMode.equals("arcade")){
                            changeScene(event,"BoardArcade.fxml", "Game Mode", player1, username, gameMode, loginSession);
                        } else {
                            changeScene(event,"BoardTrad.fxml", "Game Mode", player1, username, gameMode, loginSession);
                        }

                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static int getStoneCount() {
//        return STONE_COUNT;
//    }
}

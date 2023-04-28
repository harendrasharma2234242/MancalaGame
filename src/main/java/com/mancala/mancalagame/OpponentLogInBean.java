package com.mancala.mancalagame;

import com.mancala.mancalagame.gamecontroller.BoardController;
import com.mancala.mancalagame.gamecontroller.BoardControllerArcade;
import com.mancala.mancalagame.query.QueryUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class OpponentLogInBean {
    static QueryUtility queryUtils = new QueryUtility();
    private static final String DBURL = queryUtils.getDBURL();
    private static final String DBNAME = queryUtils.getDBNAME();
    private static final String PASS = queryUtils.getPASS();
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String player1, String player2, String gameMode) {
        Parent root = null;
        if (player1 != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                root = loader.load();
                if (gameMode.equals("arcade")){
                    BoardControllerArcade game = loader.getController();
                    game.setPlayer(player1, player2);
                } else {
                    BoardController traditional = loader.getController();
                    traditional.setPlayer(player1, player2);
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

    public static void logInUser(ActionEvent event, String username, String password, String player1, String gameMode) {
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
                        if (gameMode.equals("arcade")){
                            changeScene(event,"BoardArcade.fxml", "Game Mode", player1, username, gameMode);
                        } else {
                            changeScene(event,"BoardTrad.fxml", "Game Mode", player1, username, gameMode);
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
}

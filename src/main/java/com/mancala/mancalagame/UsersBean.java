package com.mancala.mancalagame;

import com.mancala.mancalagame.opponentcontroller.OpponentAndGameMode;
import com.mancala.mancalagame.query.QueryUtility;
import com.mancala.mancalagame.query.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.sql.*;

/**
 *
 */
public class UsersBean {
    static QueryUtility queryUtils = new QueryUtility();
    private static final String DBURL = queryUtils.getDBURL();
    private static final String DBNAME = queryUtils.getDBNAME();
    private static final String PASS = queryUtils.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String sessionId) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                root = loader.load();
                OpponentAndGameMode loggedInController = loader.getController();
                loggedInController.saveUser1(username, sessionId);
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
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void SignUp(ActionEvent event, String name, String username, String password) {
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            final String LOGIN_QUERY = queryUtils.getLOGIN_QUERY();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement psCheckUserExist = connection.prepareStatement(LOGIN_QUERY);
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User is already existed!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                final String SIGNUP_QUERY = queryUtils.getSIGNUP();
                PreparedStatement psInsert = connection.prepareStatement(SIGNUP_QUERY);
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, name);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User has been registered. /nWait for admin confirmation for activation account.");
                alert.show();
                changeScene(event, "PlayerLogin.fxml", "Sign in", username, "");
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

    public static void logInUser(ActionEvent event, String username, String password) {
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
                        final String SET_SESSION = queryUtils.getSaveSession();
                        preparedStatement = connection.prepareStatement(SET_SESSION);
                        preparedStatement.setString(1, sessionId);
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                        changeScene(event, "OpponentAndGameMode.fxml", "Choose", username, sessionId);
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

    public static void logInAdmin(ActionEvent event, String username, String password) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            final String LOGIN_QUERY = queryUtils.getAdminLoginQuery();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Admin not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            } else {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        changeScene(event, "AdminDashboard.fxml", "Welcome to the game", username, "");
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


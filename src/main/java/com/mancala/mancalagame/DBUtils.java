package com.mancala.mancalagame;

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

public class DBUtils {
    static QueryUtility queryUtils=  new QueryUtility();
    private static final String DBURL = queryUtils.getDBURL();
    private static final String DBNAME = queryUtils.getDBNAME();
    private  static final String PASS = queryUtils.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null;
        if(username !=null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void SignUp(ActionEvent event, String name, String username, String password){
        Connection connection = null;
        ResultSet resultSet = null;

        try{
            final String LOGIN_QUERY = queryUtils.getLOGIN_QUERY();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement psCheckUserExist = connection.prepareStatement(LOGIN_QUERY);
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();
            if(resultSet.isBeforeFirst()){
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
                changeScene(event, "logged-in.fxml", "welcome to the Game", username);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            final String LOGIN_QUERY = queryUtils.getLOGIN_QUERY();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            } else {
                while(resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)){
                        changeScene(event, "logged-in.fxml", "Welcome to the game", username);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }

}

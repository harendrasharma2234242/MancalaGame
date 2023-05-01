package com.mancala.mancalagame;

import com.mancala.mancalagame.admincontroller.AdminDashboard;
import com.mancala.mancalagame.admincontroller.NewUserRequests;
import com.mancala.mancalagame.query.AdminQuery;
import com.mancala.mancalagame.query.UsersQuery;
import com.mancala.mancalagame.utility.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
public class AdminBean {

    static AdminQuery adminQuery = new AdminQuery();
    static UsersQuery queryUtils = new UsersQuery();
    static DBConnection dbConnection = new DBConnection();
    private static final String DBURL = dbConnection.getDBURL();
    private static final String DBNAME = dbConnection.getDBNAME();
    private static final String PASS = dbConnection.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, List<List<String>> resultSet) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                root = loader.load();
                if (fxmlFile.equals("AdminDashboard.fxml")){
                    AdminDashboard adminDashboard = loader.getController();
                    adminDashboard.setUserName(username);
                } else if (fxmlFile.equals("newUserRequests.fxml")) {

                    NewUserRequests newUserRequests = loader.getController();
                    newUserRequests.setNewUser(resultSet);
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
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
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
                        changeScene(event, "AdminDashboard.fxml", "Welcome to the dashboard", username, null);
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

    public static List<List<String>> newUsers(){
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        Connection connection = null;
        ResultSet resultSet = null;
        final String GET_NEW_USER = adminQuery.getGetNewUsers();
        try {
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(GET_NEW_USER);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    ArrayList<String> users = new ArrayList<String>();
                    users.add(resultSet.getString("username"));
                    if (resultSet.getBoolean("is_Active")){
                        users.add("1");
                    } else {
                        users.add("0");
                    }
                    int userId = resultSet.getInt("user_id");
                    users.add(""+userId+"");
                    listOfLists.add(users);
                }
            }
            return listOfLists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateUsers(int userId){
        Connection connection = null;
        final String ACTIVE_NEW_USER = adminQuery.updateUser();
        try {
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(ACTIVE_NEW_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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


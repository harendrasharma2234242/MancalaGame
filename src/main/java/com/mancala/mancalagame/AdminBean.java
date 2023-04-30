package com.mancala.mancalagame;

import com.mancala.mancalagame.admincontroller.AdminDashboard;
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


/**
 *
 */
public class AdminBean {
    static UsersQuery queryUtils = new UsersQuery();
    static DBConnection dbConnection = new DBConnection();
    private static final String DBURL = dbConnection.getDBURL();
    private static final String DBNAME = dbConnection.getDBNAME();
    private static final String PASS = dbConnection.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                root = loader.load();
                if (fxmlFile.equals("AdminDashboard.fxml")){
                    AdminDashboard adminDashboard = loader.getController();
                    adminDashboard.setUserName(username);
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
                        changeScene(event, "AdminDashboard.fxml", "Welcome to the dashboard", username);
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


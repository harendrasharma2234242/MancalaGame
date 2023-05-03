package com.mancala.mancalagame;

import com.mancala.mancalagame.usercontroller.*;
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
import java.util.ArrayList;

/**
 *
 */
public class UsersBean {
    static UsersQuery queryUtils = new UsersQuery();
    static DBConnection dbConnection = new DBConnection();
    private static final String DBURL = dbConnection.getDBURL();
    private static final String DBNAME = dbConnection.getDBNAME();
    private static final String PASS = dbConnection.getPASS();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String sessionId, InputStream profileImage, ArrayList<String> userProfileData) {
        Parent root = null;
        if (username != null) {
            try {

                if (fxmlFile.equals("OpponentAndGameMode.fxml")){
                    FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                    root = loader.load();
                    OpponentAndGameMode loggedInController = loader.getController();
                    loggedInController.saveUser1(username, sessionId, profileImage);
                } else if (fxmlFile.equals("PlayerProfile.fxml")) {
                    FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                    root = loader.load();
                    PlayerProfileController.userName(username);
                } else if (fxmlFile.equals("Leaderboard.fxml")) {
                    FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                    root = loader.load();
                    LeaderboardController.userName(username, sessionId, profileImage);
                } else if (fxmlFile.equals("FavouriteUsers.fxml")) {
                    FavouriteUsersController.userName(username, sessionId, profileImage);
                    FXMLLoader loader = new FXMLLoader(UsersBean.class.getResource(fxmlFile));
                    root = loader.load();
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

    public static void SignUp(ActionEvent event, String name, String username, String password, InputStream profileImage) {
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
                psInsert.setBlob(4, profileImage);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User has been registered. \nWait for admin confirmation for activation account.");
                alert.show();
                changeScene(event, "PlayerLogin.fxml", "Sign in", username, "", null, null);
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
                        InputStream profileImage = resultSet.getBinaryStream("profileImage");
                        final String SET_SESSION = queryUtils.getSaveSession();
                        preparedStatement = connection.prepareStatement(SET_SESSION);
                        preparedStatement.setString(1, sessionId);
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                        changeScene(event, "OpponentAndGameMode.fxml", "Choose", username, sessionId, profileImage, null);
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

    public static ArrayList<String> getUserProfile(String username){
        ArrayList<String> userProfileData = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        final String PROFILE_QUERY = queryUtils.getUserProfileInfo();
        try {
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement userProfileStatement = connection.prepareStatement(PROFILE_QUERY);
            userProfileStatement.setString(1, username);
            userProfileStatement.setString(2, username);
            userProfileStatement.setString(3, username);
            resultSet = userProfileStatement.executeQuery();
            while (resultSet.next()){
                userProfileData.add(resultSet.getString("username"));
                userProfileData.add(String.valueOf(resultSet.getDate("lastLogin")));
                userProfileData.add(String.valueOf(resultSet.getInt("totalGame")));
                userProfileData.add(String.valueOf(resultSet.getInt("totalWin")));
                int loss = resultSet.getInt("totalGame")-resultSet.getInt("totalWin");
                userProfileData.add(String.valueOf(loss));
                float totalWin = resultSet.getInt("totalWin");
                float totalGame = resultSet.getInt("totalGame");
                float winPercentage = (totalWin/totalGame)*100;
                if (totalGame == 0) {
                    userProfileData.add("0.0");
                } else {
                    userProfileData.add(String.valueOf(winPercentage));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return userProfileData;
    }

    public static ArrayList<String> getAllUsers(){
        ArrayList<String> userProfileData = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        final String PROFILE_QUERY = queryUtils.getAllUsers();
        try {
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement userProfileStatement = connection.prepareStatement(PROFILE_QUERY);
            resultSet = userProfileStatement.executeQuery();
            while (resultSet.next()) {
                userProfileData.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return userProfileData;
    }

    public static void addFavourite(String username1, String username2) {
        Connection connection = null;
        try {
            ArrayList<String> existingFaves = getAllFavourites(username1);
            if (!existingFaves.contains(username2)) {
                final String FAVOURITE_QUERY = queryUtils.getFavourite();
                connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
                PreparedStatement addFave = connection.prepareStatement(FAVOURITE_QUERY);
                addFave.setString(1, username1);
                addFave.setString(2, username2);
                addFave.executeUpdate();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(username1 + " has already favourited " + username2);
                alert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void removeFavourite(String username1, String username2) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            final String FAVOURITE_QUERY = queryUtils.getUnfavourite();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement removeFave = connection.prepareStatement(FAVOURITE_QUERY);
            removeFave.setString(1, username1);
            removeFave.setString(2, username2);
            removeFave.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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

    public static ArrayList<String> getAllFavourites(String username1) {
        ArrayList<String> favourites = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            final String FAVOURITE_QUERY = queryUtils.getAllFavourites();
            connection = DriverManager.getConnection(DBURL, DBNAME, PASS);
            PreparedStatement faves = connection.prepareStatement(FAVOURITE_QUERY);
            faves.setString(1, username1);
            resultSet = faves.executeQuery();
            while (resultSet.next()) {
                favourites.add(resultSet.getString("username2"));
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
        return favourites;
    }
}


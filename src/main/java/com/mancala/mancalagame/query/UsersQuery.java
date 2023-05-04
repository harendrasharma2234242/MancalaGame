package com.mancala.mancalagame.query;

import java.util.ArrayList;
/**This Game query class is used to store all the queries related to game
 * @author Harendra Sharma
 * @author Alex Wadge
 * @version 2.0
 * */
public class UsersQuery {
    private static final String LOGIN_QUERY = "SELECT password, is_Active, is_admin, profileImage from " +
            "user where username= ? AND is_admin = 0";
    private static final String ADMIN_LOGIN_QUERY = "SELECT password from user where username= ? AND is_admin = 1";
    private static final String SIGNUP = "INSERT INTO user (username, password, name, profileImage) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SAVE_SESSION = "INSERT INTO loginsessions (sessionId, username) VALUES (?, ?)";
    private static final String GET_USER_PROFILE = "SELECT u.username, u.profileImage, MAX(logins.logInDateTime) " +
            "AS lastLogin, (SELECT COUNT(player1) FROM gamerecord WHERE player1 = ?) AS totalGame, (SELECT COUNT(winner)" +
            " FROM gamerecord WHERE winner = ?) AS totalWin FROM USER AS u\n" +
            "LEFT JOIN loginsessions AS logins ON logins.username = u.username\n" +
            "WHERE u.username = ?";
    private static final String GET_ALL_USERS = "SELECT username FROM User WHERE is_admin = false AND is_Active = true";
    private static final String FAVOURITE = "INSERT INTO favourites VALUES (?, ?)";
    private static final String UNFAVOURITE = "DELETE FROM favourites WHERE (username1=? AND username2=?)";
    private static final String GET_FAVOURITES = "SELECT username2 FROM favourites WHERE username1=?";

    /**
     * Get query to get a log in.
     * @return query to get a log in.
     */
    public String getLOGIN_QUERY() {
        return LOGIN_QUERY;
    }

    /**
     * Get query to get a sign up.
     * @return query to get a sign up.
     */
    public String getSIGNUP() {
        return SIGNUP;
    }

    /**
     * Get query to check if admin
     * @return query to check if admin
     */
    public String getAdminLoginQuery() {
        return ADMIN_LOGIN_QUERY;
    }

    /**
     * Get query to save a game session.
     * @return query to save a game session.
     */
    public  String getSaveSession() { return  SAVE_SESSION; }

    /**
     * Get query to get a user profile.
     * @return query to get a user profile.
     */
    public String getUserProfileInfo() {
        return GET_USER_PROFILE;
    }

    /**
     * Get query to get list of all users.
     * @return query to get list of all users.
     */
    public String getAllUsers() {
        return GET_ALL_USERS;
    }

    /**
     * Get query to add a favourite to the database
     * @return query to add a favourite to the database
     */
    public String getFavourite(){
        return FAVOURITE;
    }

    /**
     * Get query to remove a favourite from the database
     * @return query to remove a favourite from the database
     */
    public String getUnfavourite() {
        return UNFAVOURITE;
    }

    /**
     * Get query to get a list of a player's favourites
     * @return query to get a list of a player's favourites
     */
    public String getAllFavourites() {
        return GET_FAVOURITES;
    }


}
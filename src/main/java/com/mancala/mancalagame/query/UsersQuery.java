package com.mancala.mancalagame.query;

public class UsersQuery {
    private static final String LOGIN_QUERY = "SELECT password, is_Active, is_admin, profileImage from user where username= ? AND is_admin = 0";
    private static final String ADMIN_LOGIN_QUERY = "SELECT password from user where username= ? AND is_admin = 1";
    private String SIGNUP = "INSERT INTO user (username, password, name, profileImage) VALUES (?, ?, ?, ?)";

    private static final String SAVE_SESSION = "INSERT INTO loginsessions (sessionId, username) VALUES (?, ?)";
    public String getLOGIN_QUERY() {
        return LOGIN_QUERY;
    }

    public String getSIGNUP() {
        return SIGNUP;
    }

    public String getAdminLoginQuery() {
        return ADMIN_LOGIN_QUERY;
    }
    public  String getSaveSession() { return  SAVE_SESSION; }


}
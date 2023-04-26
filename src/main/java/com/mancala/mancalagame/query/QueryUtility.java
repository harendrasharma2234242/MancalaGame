package com.mancala.mancalagame.query;

public class QueryUtility {
    private static final String DBURL = "jdbc:mysql://localhost:3306/mancala";
    private static final String DBNAME = "root";
    private static final String PASS = "123456";
    private static final String LOGIN_QUERY = "SELECT password, is_Active, is_admin from user where username= ? AND is_admin = 0";
    private static final String ADMIN_LOGIN_QUERY = "SELECT password from user where username= ? AND is_admin = 1";
    private String SIGNUP = "INSERT INTO user (username, password, name) VALUES (?,?, ?)";

    public String getDBURL() {
        return DBURL;
    }

    public String getPASS() {
        return PASS;
    }

    public String getDBNAME() {
        return DBNAME;
    }


    public String getLOGIN_QUERY() {
        return LOGIN_QUERY;
    }

    public String getSIGNUP() {
        return SIGNUP;
    }

    public String getAdminLoginQuery() {
        return ADMIN_LOGIN_QUERY;
    }


}
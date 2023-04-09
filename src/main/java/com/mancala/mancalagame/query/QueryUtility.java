package com.mancala.mancalagame.query;

public class QueryUtility {
    private static  final String DBURL = "jdbc:mysql://localhost:3306/mancala";
    private static final String DBNAME = "root";
    private static final String PASS = "123456";
    private static final String LOGIN_QUERY = "SELECT password from user where username= ?";
    private String  SIGNUP = "INSERT INTO user (username, password, name) VALUES (?,?, ?)";
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



}

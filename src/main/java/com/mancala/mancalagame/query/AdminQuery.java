package com.mancala.mancalagame.query;

public class AdminQuery {
    private static final String GET_NEW_USERS = "SELECT * from user where is_admin = 0 AND is_Active = 0";
    private static final String ACTIVATE_USER = "UPDATE user set is_Active = 1 where user_id = ?";
//    private static final String GET_FREQUENT_USERS = "SELECT * from users";
    private static final String GET_FREQUENT_USERS = "SELECT username, COUNT(username) AS count FROM loginsessions GROUP BY username order by count desc limit 5;";

    public String getGetNewUsers(){
        return  GET_NEW_USERS;
    }
    public String updateUser(){
        return ACTIVATE_USER;
    }
    public String getGetFrequentUsers(){
        return GET_FREQUENT_USERS;
    }
}

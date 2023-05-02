package com.mancala.mancalagame.query;

public class AdminQuery {
    private static final String GET_NEW_USERS = "SELECT * from user where is_admin = 0 AND is_Active = 0";
    private static final String ACTIVATE_USER = "UPDATE user set is_Active = 1 where user_id = ?";
    private static final String getAllSpecialCase = "SELECT SUM(doublePoint) as doublePoint, SUM(continueTurn) as continueTurn, SUM(reverseTurn) as reverseTurn, SUM(switchSide) as switchSide, SUM(halfHand) as halfHand from gamerecord";
    private static final String GET_FREQUENT_USERS = "SELECT username, COUNT(username) AS loginCount, MAX(logInDateTime) AS lastLogin FROM loginsessions \n" +
            "GROUP BY username\n" +
            "ORDER BY COUNT(username)\n" +
            "DESC LIMIT 5";

    public String getGetNewUsers(){
        return  GET_NEW_USERS;
    }
    public String updateUser(){
        return ACTIVATE_USER;
    }
    public String getGetAllSpecialCaseQuery(){
        return getAllSpecialCase;
    }
    public String getFrequentUsersQuery(){
        return GET_FREQUENT_USERS;
    }
}

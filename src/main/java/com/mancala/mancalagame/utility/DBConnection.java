package com.mancala.mancalagame.utility;

/**This DBConnection file is used for keeping database information
 * @author Harendra Sharma
 * @version 1.0*/
public class DBConnection {
    private static final String DBURL = "jdbc:mysql://localhost:3306/mancala";
    private static final String DBNAME = "root";
    private static final String PASS = "";
    public String getDBURL() {
        return DBURL;
    }

    public String getPASS() {
        return PASS;
    }

    public String getDBNAME() {
        return DBNAME;
    }
}

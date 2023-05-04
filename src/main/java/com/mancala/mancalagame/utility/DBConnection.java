package com.mancala.mancalagame.utility;

/**This DBConnection file is used for keeping database information
 * @author Harendra Sharma
 * @version 1.0*/
public class DBConnection {
    private static final String DBURL = "jdbc:mysql://localhost:3306/mancala";
    private static final String DBNAME = "root";
    private static final String PASS = "";

    /**
     * Get database url query
     * @return database url query
     */

    public String getDBURL() {
        return DBURL;
    }
    /**
     * Get database password query
     * @return database password query
     */
    public String getPASS() {
        return PASS;
    }
    /**
     * Get database name query
     * @return database name query
     */
    public String getDBNAME() {
        return DBNAME;
    }
}

package com.mancala.mancalagame.utility;

import java.util.UUID;
/**
 * The Utility class for the generating session id.
 * @author Harendra Sharma
 * @version 1.0
 */
public class Utility {
/** getRandomKey methods generate uuid which is being used as unique sessionId*/
    public static UUID getRandomKey() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}

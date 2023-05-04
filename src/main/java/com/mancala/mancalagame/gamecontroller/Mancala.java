package com.mancala.mancalagame.gamecontroller;
/**
 * Class to define mancala objects.
 * @author Alex Wadge
 * @version 1.0
 */
public class Mancala {
    private int count;

    /**
     * Constructs a mancala with a given number of stones in it.
     * @param count the number of stones
     */
    public Mancala(int count) {
        this.count = count;
    }
    /**
     * Set the number of stones in a mancala.
     * @param newCount the new number of stones.
     */
    public void setCount(int newCount) {
        count = newCount;
    }
    /**
     * Return the number of stones in a mancala.
     * @return the number of stones in the mancala.
     */
    public int getCount() {
        return count;
    }
}

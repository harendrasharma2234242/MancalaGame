package com.mancala.mancalagame.gamecontroller;

/**
 * Class to define hole objects.
 * @author Alex Wadge
 * @version 1.0
 */
public class Hole {
    private int count;

    /**
     * Constructs a hole with a given number of stones in it.
     * @param count the number of stones
     */
    public Hole(int count) {
        this.count = count;
    }

    /**
     * Set the number of stones in a hole.
     * @param newCount the new number of stones.
     */
    public void setCount(int newCount) {
        count = newCount;
    }

    /**
     * Return the number of stones in a hole.
     * @return the number of stones in the hole.
     */
    public int getCount() {
        return count;
    }
}

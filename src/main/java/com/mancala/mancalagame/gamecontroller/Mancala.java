package com.mancala.mancalagame.gamecontroller;

public class Mancala {
    private int count;

    public Mancala(int count) {
        this.count = count;
    }

    public void setCount(int newCount) {
        count = newCount;
    }

    public int getCount() {
        return count;
    }
}

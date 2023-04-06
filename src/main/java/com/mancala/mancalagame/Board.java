package com.mancala.mancalagame;

import java.util.ArrayList;

public class Board {
    private ArrayList<Hole> holes = new ArrayList<Hole>();
    private ArrayList<Mancala> mancalas = new ArrayList<Mancala>();

    public Board(ArrayList<Hole> holes, ArrayList<Mancala> mancalas) {
        this.holes = holes;
        this.mancalas = mancalas;
    }

    public int getHole(int holeNumber) {
        return holes.get(holeNumber).getCount();
    }

    public int getMancala(int holeNumber) {
        return mancalas.get(holeNumber).getCount();
    }

    public void printHoles() {
        for (Hole i : holes) {
            System.out.println(i.getCount());
        }
    }
}

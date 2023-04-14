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
        for (int i = 11; i >= 6; i--) {
            System.out.print(holes.get(i).getCount() + " ");
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print(holes.get(i).getCount() + " ");
        }
        System.out.println();
    }

    public void printMancalas() {
        for (Mancala m : mancalas) {
            System.out.println("**" + m.getCount() + "**");
        }
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 11; i >= 6; i--) {
            System.out.print(holes.get(i).getCount() + " ");
        }
        System.out.println();
        System.out.println(mancalas.get(1).getCount() + "              " + mancalas.get(0).getCount());
        System.out.print("   ");
        for (int i = 0; i < 6; i++) {
            System.out.print(holes.get(i).getCount() + " ");
        }
    }
}

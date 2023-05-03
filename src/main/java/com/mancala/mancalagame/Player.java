package com.mancala.mancalagame;

import java.util.ArrayList;

public class Player extends User{
    private ArrayList<String> favourites = new ArrayList<>();
    public Player(String username, String firstName, String lastName, ArrayList<String> favourites) {
        super(username, firstName, lastName);
        this.favourites = favourites;
    }

    public void addFavourite(String otherUser) {
        favourites.add(otherUser);
    }

    public void removeFavourite(String otherUser) {
        favourites.remove(otherUser);
    }
}

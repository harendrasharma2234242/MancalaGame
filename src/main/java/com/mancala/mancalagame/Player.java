package com.mancala.mancalagame;

import java.util.ArrayList;

public class Player extends User{
    private ArrayList<Player> favourites = new ArrayList<>();
    public Player(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }

    public void addFavourite(Player otherUser) {
        favourites.add(otherUser);
    }

    public void removeFavourite(Player otherUser) {
        favourites.remove(otherUser);
    }
}

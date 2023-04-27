package com.mancala.mancalagame;

public class User {
    private final String username;
    private final String firstName;
    private final String lastName;
//    private Byte[][] image;
//not sure how images work so leaving it out for now
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void updateProfileImage() {
        //blank
    }

    public void login() {
        //blank
    }

}

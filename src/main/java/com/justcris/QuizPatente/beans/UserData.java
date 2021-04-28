/*
 * Copyright (c) 26/4/2021. Scapin Cristian
 */

package com.justcris.QuizPatente.beans;

public class UserData {
    private String username;
    private boolean renewal;
    private int score;

    public UserData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRenewal() {
        return renewal;
    }

    public void setRenewal(boolean renewal) {
        this.renewal = renewal;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

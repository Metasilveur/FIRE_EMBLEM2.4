package com.example.fire_emblem;

import java.io.Serializable;

public class Battle implements Serializable {
    public Character character1;
    public Character character2;
    public String score1;
    public String score2;
    public String score1_txt;
    public String score2_txt;
    public String turn;

    public Battle(Character character1, Character character2, String score1, String score2, String score1_txt, String score2_txt, String turn) {
        this.character1 = character1;
        this.character2 = character2;
        this.score1 = score1;
        this.score2 = score2;
        this.score1_txt = score1_txt;
        this.score2_txt = score2_txt;
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public String getScore1_txt() {
        return score1_txt;
    }

    public String getScore2_txt() {
        return score2_txt;
    }

    public String getScore1() {
        return score1;
    }

    public Character getCharacter1() {
        return character1;
    }

    public Character getCharacter2() {
        return character2;
    }

    public String getScore2() {
        return score2;
    }
}

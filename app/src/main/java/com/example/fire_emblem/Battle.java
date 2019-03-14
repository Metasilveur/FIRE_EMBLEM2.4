package com.example.fire_emblem;

import java.io.Serializable;

public class Battle implements Serializable {
    public Character character1;
    public Character character2;
    public String score1;
    public String score2;

    public Battle(Character character1, Character character2, String score1, String score2) {
        this.character1 = character1;
        this.character2 = character2;
        this.score1 = score1;
        this.score2 = score2;
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

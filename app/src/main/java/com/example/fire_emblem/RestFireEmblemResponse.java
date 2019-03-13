package com.example.fire_emblem;
import java.util.List;

public class RestFireEmblemResponse {

    private List<Character> heroes;
    private List<Skills> skills;

    public List<Skills> getSkills() { return skills; }

    public void setSkills(List<Skills> skills) { this.skills = skills; }

    public List<Character> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Character> heroes) {
        this.heroes = heroes;
    }
}



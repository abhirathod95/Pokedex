package com.pokedex.falcon.pokedex;
import java.util.List;

/**
 * Created by Abhi on 2/18/2015.
 */
public class Pokedex {
    private String name;
    private List<Pokemon> pokemon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

}


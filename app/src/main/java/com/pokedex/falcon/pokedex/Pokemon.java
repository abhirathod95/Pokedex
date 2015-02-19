package com.pokedex.falcon.pokedex;

import java.util.List;

/**
 * Created by Abhi on 2/18/2015.
 */
public class Pokemon {

    private String resource_uri;
    private String name;
    private String weight;
    private List<Type> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return resource_uri;
    }

    public void setUri (String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
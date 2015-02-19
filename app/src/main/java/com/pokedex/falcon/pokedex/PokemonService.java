package com.pokedex.falcon.pokedex;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Abhi on 2/18/2015.
 */
public interface PokemonService {

    @GET("/pokemon/{index}")
    void getPokemon(@Path("index") int index, Callback<Pokemon> callback);

    @GET("/pokedex/{index}")
    void getPokedex(@Path("index") int index, Callback<Pokedex> callback);
}

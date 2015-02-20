package com.pokedex.falcon.pokedex;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Abhi on 2/18/2015.pokemonService.getPokedex(1,callback);
 */
public class PokemonInfo extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Pokemon pokemon = getIntent().getExtras().getParcelable("pokemon");
        int nationalId = Integer.parseInt(pokemon.getUri().substring(15).replaceAll("[\\D]", ""));
        pokemon.setNationalId(nationalId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info);

        final TextView TextView = (TextView) findViewById(R.id.textView);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://pokeapi.co/api/v1")
                .build();

        PokemonService pokemonService = restAdapter.create(PokemonService.class);

        Callback<Pokemon> callback = new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, Response response) {
                TextView.setText(pokemon.getAttack());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
        pokemonService.getPokemon(pokemon.getNationalId(),callback);
    }
}
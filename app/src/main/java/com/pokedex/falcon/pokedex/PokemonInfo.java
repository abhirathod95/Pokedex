package com.pokedex.falcon.pokedex;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Abhi on 2/18/2015.
 */
public class PokemonInfo extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        String pokemonName = getIntent().getExtras().getString("main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info);

        TextView TextView = (TextView) findViewById(R.id.textView);
        TextView.setText(pokemonName);
    }
}
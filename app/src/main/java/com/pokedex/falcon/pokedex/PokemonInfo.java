package com.pokedex.falcon.pokedex;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Abhi on 2/18/2015.pokemonService.getPokedex(1,callback);
 */
public class PokemonInfo extends Activity {

    private Bitmap bitmap;
    private ProgressDialog pDialog;
    private ImageView imageView;
    private TextView pokemonTitle, pokemonAttack, pokemonDefense, pokemonHp, pokemonSpecialAttack,
    pokemonSpecialDefense;

    protected void onCreate(Bundle savedInstanceState) {
        Pokemon pokemon = getIntent().getExtras().getParcelable("pokemon");
        final int nationalId = Integer.parseInt(pokemon.getUri().substring(15).replaceAll("[\\D]", ""));
        pokemon.setNationalId(nationalId);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info);

        pokemonTitle = (TextView) findViewById(R.id.textView);
        pokemonAttack = (TextView) findViewById(R.id.AttackNumber);
        pokemonDefense  = (TextView) findViewById(R.id.DefenseNumber);
        pokemonHp  = (TextView) findViewById(R.id.HpNumber);
        pokemonSpecialAttack  = (TextView) findViewById(R.id.SpecialAttackNumber);
        pokemonSpecialDefense = (TextView) findViewById(R.id.SpecialDefenseNumber);
        imageView = (ImageView) findViewById(R.id.imageView);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://pokeapi.co/api/v1")
                .build();

        PokemonService pokemonService = restAdapter.create(PokemonService.class);

        Callback<Pokemon> callback = new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, Response response) {
                pokemonTitle.setText(pokemon.toString());
                pokemonAttack.setText(pokemon.getAttack());
                pokemonDefense.setText(pokemon.getDefense());
                pokemonHp.setText(pokemon.getHp());
                pokemonSpecialAttack.setText(pokemon.getSp_atk());
                pokemonSpecialDefense.setText(pokemon.getSp_def());
                if(nationalId <= 718)
                    new LoadImage().execute("http://pokeapi.co/media/img/" + nationalId + ".png");

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
        pokemonService.getPokemon(pokemon.getNationalId(),callback);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PokemonInfo.this);
            pDialog.setMessage("Loading Information ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                imageView.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(PokemonInfo.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
package com.pokedex.falcon.pokedex;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private ArrayList<Pokemon> objects;
    private ArrayAdapter<Pokemon> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        objects = new ArrayList<Pokemon>();
        adapter = new ArrayAdapter<Pokemon>(this, R.layout.list_item, objects);

        listView.setAdapter(adapter);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://pokeapi.co/api/v1")
                .build();

        PokemonService pokemonService = restAdapter.create(PokemonService.class);

        Callback<Pokedex> callback = new Callback<Pokedex>() {
            @Override
            public void success(Pokedex pokedex, Response response) {
                List<Pokemon> pokemons = pokedex.getPokemon();
                for (Pokemon pokemon: pokemons){
                    objects.add(pokemon);
                }
                Collections.sort(objects, Pokemon.PokeNatIdComparator);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
        pokemonService.getPokedex(1,callback);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Pokemon pokemon = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, PokemonInfo.class);
                intent.putExtra ("pokemon", pokemon);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
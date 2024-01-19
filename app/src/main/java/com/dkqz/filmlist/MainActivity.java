package com.dkqz.filmlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

import models.Film;
import adapters.FilmsListAdapter;

public class MainActivity extends AppCompatActivity {

    public final ArrayList<Film> data = new ArrayList<>();
    public FilmsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        adapter = new FilmsListAdapter(this, data);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete this note");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", (dialogInterface, n) -> {
                data.remove(i);
                adapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("No", null);

            builder.show();

            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_1) {
            Intent intent = new Intent(getApplicationContext(), FilmActivity.class);
            startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            Film film = (Film) data.getSerializableExtra("film");

            this.data.add(film);
            adapter.notifyDataSetChanged();
            ((ListView) findViewById(R.id.listView)).smoothScrollToPosition(this.data.size());
        }
    }
}

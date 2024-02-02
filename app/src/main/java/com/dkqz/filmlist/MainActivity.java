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
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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

        Film.films.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Film film = ds.getValue(Film.class);
                    data.add(film);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete this note");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", (dialogInterface, n) -> Film.films.child(data.get(i).getKey()).removeValue());
            builder.setNegativeButton("No", null);

            builder.show();
            return true;
        });

//        listView.setOnItemClickListener((adapterView, view, i, l) -> {
//            Intent intent = new Intent(getApplicationContext(), FilmActivity.class);
//
//            intent.putExtra("film", data.get(i));
//            intent.putExtra("index", i);
//
//            startActivityForResult(intent, 2);
//        });
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

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            ((ListView) findViewById(R.id.listView)).smoothScrollToPosition(this.data.size());
        }

        // temporary unavailable due to not implementing editing
        else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            int index = data.getIntExtra("index", -1);
            ((ListView) findViewById(R.id.listView)).smoothScrollToPosition(index + 1);
        }
    }
}

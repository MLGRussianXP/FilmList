package com.dkqz.filmlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;

import FilmsList.Film;
import FilmsList.FilmsListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Film> data = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);

        FilmsListAdapter adapter = new FilmsListAdapter(this, data);
        listView.setAdapter(adapter);

        EditText etName = findViewById(R.id.etAdd);
        EditText etDuration = findViewById(R.id.etDuration);
        CheckBox cbRecommended = findViewById(R.id.cbRecommended);
        Button btnAdd = findViewById(R.id.btnAdd);

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

        btnAdd.setOnClickListener(v -> {

            // get name
            String name = etName.getText().toString();

            if (name.equals("")) {
                etName.setError("Required field!");
                return;
            }

            // get duration
            String duration = etDuration.getText().toString();

            if (duration.equals("")) {
                etDuration.setError("Required field!");
                return;
            }

            // get checkbox
            boolean recommended = cbRecommended.isChecked();

            // add to list
            data.add(new Film(
                name,
                Integer.parseInt(duration),
                recommended
            ));
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(data.size());

            // reset
            etName.setText("");
            etDuration.setText("");
            cbRecommended.setChecked(false);
        });
    }
}

package com.dkqz.filmlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import models.Film;

public class FilmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        EditText etName = findViewById(R.id.etAdd);
        EditText etDuration = findViewById(R.id.etDuration);
        CheckBox cbRecommended = findViewById(R.id.cbRecommended);
        Button btnAdd = findViewById(R.id.btnAdd);

        Intent intent = getIntent();

        boolean edit;
        Film source = (Film) intent.getSerializableExtra("film");

        if (source != null) {
            edit = true;

            etName.setText(source.getName());
            etDuration.setText("" + source.getDuration());
            cbRecommended.setChecked(source.isRecommended());

            btnAdd.setText("Edit");
        }
        else
            edit = false;

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

            // return to main activity
            Intent data = new Intent();

            Film film = new Film(
                name,
                Integer.parseInt(duration),
                recommended
            );

            data.putExtra("film", film);
            if (edit)
                data.putExtra("index", intent.getIntExtra("index", 0));

            setResult(Activity.RESULT_OK, data);
            finish();
        });
    }
}

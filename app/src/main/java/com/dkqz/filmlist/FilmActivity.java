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

            data.putExtra(
                    "film",
                    new Film(
                        name,
                        Integer.parseInt(duration),
                        recommended
                    )
            );
            setResult(Activity.RESULT_OK, data);

            finish();
        });
    }
}

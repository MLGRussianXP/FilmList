package models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Film implements Serializable {
    private String key = "";

    private String name = "";

    private int duration = 0;
    private boolean recommended = false;

    public static FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static DatabaseReference films = db.getReference().child("Films");

    public Film(String name, int duration, boolean recommended) {
        this.name = name;
        this.duration = duration;
        this.recommended = recommended;
    }

    public Film() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    @NonNull
    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", recommended=" + recommended +
                '}';
    }
}

package models;

import java.io.Serializable;

public class Film implements Serializable {
    private String name;
    private int duration;
    private boolean recommended;

    public Film(String name, int duration, boolean recommended) {
        this.name = name;
        this.duration = duration;
        this.recommended = recommended;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}

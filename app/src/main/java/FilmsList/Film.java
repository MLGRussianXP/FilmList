package FilmsList;

public class Film {
    private final String name;
    private final int duration;
    private final boolean recommended;

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
}

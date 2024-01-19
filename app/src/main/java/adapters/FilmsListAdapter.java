package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dkqz.filmlist.R;

import java.util.ArrayList;

import models.Film;

public class FilmsListAdapter extends ArrayAdapter<Film> {
    public FilmsListAdapter(@NonNull Context context, @NonNull ArrayList<Film> objects) {
        super(context, R.layout.film_item, objects);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Film currentFilm = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.film_item, null, false);

        TextView tvTitle = v.findViewById(R.id.tvName);
        TextView tvIsMandatory = v.findViewById(R.id.tvIsRecommended);
        TextView tvCount = v.findViewById(R.id.tvDuration);

        tvTitle.setText(currentFilm.getName());
        tvCount.setText("Duration (in minutes): " + currentFilm.getDuration());
        tvIsMandatory.setText(currentFilm.isRecommended() ? "Recommended" : "Not recommended");

        return v;
    }
}
package ru.mirea.zubarevaes.movieproject.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalDate;

import ru.mirea.zubarevaes.movieproject.domain.models.Movie;


public class SharedPrefMovieStorage implements MovieStorage {
    private SharedPreferences preferences;
    private String KeyMovieName = "MOVIE";
    private String KeyMovieID = "IDMovie";
    public SharedPrefMovieStorage(Context context) {
        preferences = context.getSharedPreferences("Lesson9", Context.MODE_PRIVATE);
    }

    @Override
    public boolean save(Movie movie) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyMovieName, movie.getName());
        editor.putInt(KeyMovieID, movie.getId());
        editor.apply();
        return true;
    }

    @Override
    public Movie get() {
        String nameMovie = preferences.getString(KeyMovieName, null);
        int idMovie = preferences.getInt(KeyMovieID, 0);
        return new Movie(idMovie, nameMovie);
    }
}
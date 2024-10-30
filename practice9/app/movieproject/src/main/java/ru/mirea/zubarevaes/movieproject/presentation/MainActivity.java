package ru.mirea.zubarevaes.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.mirea.zubarevaes.movieproject.data.model.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получение фильма
        GetFavoriteFilmUseCase getFavoriteFilmUseCase = new GetFavoriteFilmUseCase();
        Movie favoriteMovie = getFavoriteFilmUseCase.execute();

        // Сохранение фильма
        SaveFilmToFavoriteUseCase saveFilmToFavoriteUseCase = new SaveFilmToFavoriteUseCase();
        boolean isSaved = saveFilmToFavoriteUseCase.execute(favoriteMovie);

    }

}
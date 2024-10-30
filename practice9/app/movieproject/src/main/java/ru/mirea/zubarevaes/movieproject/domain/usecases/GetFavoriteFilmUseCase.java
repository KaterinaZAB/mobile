package ru.mirea.zubarevaes.movieproject;

import ru.mirea.zubarevaes.movieproject.MainActivity;

public class GetFavoriteFilmUseCase {
    public MainActivity.Movie execute(){
        return new MainActivity.Movie(3, "Game of thrones");
    }
}
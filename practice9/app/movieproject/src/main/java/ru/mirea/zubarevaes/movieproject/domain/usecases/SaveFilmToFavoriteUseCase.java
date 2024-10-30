package ru.mirea.zubarevaes.movieproject;

public class SaveFilmToFavoriteUseCase {
    public boolean execute(MainActivity.Movie movie){
        if (movie.getName().isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}

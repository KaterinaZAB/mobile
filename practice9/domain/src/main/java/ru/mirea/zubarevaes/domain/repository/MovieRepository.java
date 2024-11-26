package ru.mirea.zubarevaes.movieproject.domain.repository;


import ru.mirea.zubarevaes.movieproject.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}


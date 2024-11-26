package ru.mirea.zubarevaes.movieproject.data.storage;

import ru.mirea.zubarevaes.movieproject.domain.models.Movie;

public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}
package ru.mirea.zubarevaes.movieproject.data.repository;


import ru.mirea.zubarevaes.movieproject.data.storage.MovieStorage;
import ru.mirea.zubarevaes.movieproject.domain.models.Movie;
import ru.mirea.zubarevaes.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;

    }
    @Override
    public boolean saveMovie(Movie movie) {
        movieStorage.save(movie);
        return true;
    }

    @Override
    public Movie getMovie() {
        Movie movie = movieStorage.get();
        return movie;
    }
}

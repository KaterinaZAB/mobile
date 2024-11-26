package ru.mirea.zubarevaes.movieproject.domain.usecases;

import ru.mirea.zubarevaes.movieproject.domain.models.Movie;
import ru.mirea.zubarevaes.movieproject.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}


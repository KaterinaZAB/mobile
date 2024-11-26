package ru.mirea.zubarevaes.movieproject.domain.usecases;

import ru.mirea.zubarevaes.movieproject.presentation.MainActivity;



import ru.mirea.zubarevaes.movieproject.domain.models.Movie;
import ru.mirea.zubarevaes.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}

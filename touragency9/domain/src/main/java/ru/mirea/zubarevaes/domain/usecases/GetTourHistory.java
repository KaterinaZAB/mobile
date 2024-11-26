package ru.mirea.zubarevaes.domain.usecases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.mirea.zubarevaes.domain.models.Tour;
import ru.mirea.zubarevaes.domain.repository.ITourRepository;

public class GetTourHistory {
    private ITourRepository tourRepository;

    public GetTourHistory(ITourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> execute(int userId) {
        List<Tour> tourList = tourRepository.getFavoriteTours();

        return tourList;
    }
}
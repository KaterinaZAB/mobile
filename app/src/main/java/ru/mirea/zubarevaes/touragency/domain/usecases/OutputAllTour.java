package ru.mirea.zubarevaes.touragency.domain.usecases;

import  ru.mirea.zubarevaes.touragency.data.repository.TourRepository;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.IOutputAllTour;

import java.util.List;

public class OutputAllTour implements IOutputAllTour {
    private TourRepository tourRepository;

    public OutputAllTour(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> execute() {
        return tourRepository.getAllTours();
    }
}

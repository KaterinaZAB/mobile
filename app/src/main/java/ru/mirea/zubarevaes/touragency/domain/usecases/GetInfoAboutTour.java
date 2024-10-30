package ru.mirea.zubarevaes.touragency.domain.usecases;

import ru.mirea.zubarevaes.touragency.data.repository.TourRepository;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.IGetInfoAboutTour;

public class GetInfoAboutTour implements IGetInfoAboutTour {
    private TourRepository tourRepository;

    public GetInfoAboutTour(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Tour execute(int id) {
        return tourRepository.getTourById(id);
    }
}
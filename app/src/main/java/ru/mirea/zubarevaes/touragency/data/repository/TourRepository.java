package ru.mirea.zubarevaes.touragency.data.repository;


import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ITourRepository;

import java.util.Arrays;
import java.util.List;

public class TourRepository implements ITourRepository {
    @Override
    public List<Tour> getAllTours() {
        return Arrays.asList(
                new Tour(1, "Paris Vacation", 1000),
                new Tour(2, "Italy Getaway", 1200),
                new Tour(3, "New York Adventure", 1500)
        );
    }

    @Override
    public Tour getTourById(int id) {
        return new Tour(id, "Sample Tour", 1000);
    }
}


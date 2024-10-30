package ru.mirea.zubarevaes.touragency.data.repository;

import java.util.Arrays;
import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ITourHistoryRepository;

public class TourHistoryRepository implements ITourHistoryRepository {
    @Override
    public List<Tour> getTourHistoryByUserId(int userId) {
        return Arrays.asList(
                new Tour(1, "Paris Vacation", 1000),
                new Tour(2, "Italy Getaway", 1200)
        );
    }
}
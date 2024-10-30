package ru.mirea.zubarevaes.touragency.domain.usecases;

import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ITourHistoryRepository;

public class GetTourHistory {
    private ITourHistoryRepository tourHistoryRepository;

    public GetTourHistory(ITourHistoryRepository tourHistoryRepository) {
        this.tourHistoryRepository = tourHistoryRepository;
    }

    public List<Tour> execute(int userId) {
        return tourHistoryRepository.getTourHistoryByUserId(userId);
    }
}
package ru.mirea.zubarevaes.touragency.domain.repository;

import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;

public interface ITourHistoryRepository {
    List<Tour> getTourHistoryByUserId(int userId);
}
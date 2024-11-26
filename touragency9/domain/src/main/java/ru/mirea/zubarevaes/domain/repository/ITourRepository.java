package ru.mirea.zubarevaes.domain.repository;

import java.util.List;

import ru.mirea.zubarevaes.domain.models.Tour;

public interface ITourRepository {
    List<Tour> getAllTours();
    Tour getTourById(int id);

    List<Tour> getFavoriteTours();
}
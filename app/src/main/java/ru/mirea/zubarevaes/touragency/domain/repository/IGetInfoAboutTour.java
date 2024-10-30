package ru.mirea.zubarevaes.touragency.domain.repository;

import ru.mirea.zubarevaes.touragency.domain.models.Tour;

public interface IGetInfoAboutTour {
    Tour execute(int id);
}

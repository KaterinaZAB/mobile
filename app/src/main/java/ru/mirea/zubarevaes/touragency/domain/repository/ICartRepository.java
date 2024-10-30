package ru.mirea.zubarevaes.touragency.domain.repository;


import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;

public interface ICartRepository {
    void addTourToCart(Tour tour);
    List<Tour> getCartItems();
    void removeTourFromCart(Tour tour);
}
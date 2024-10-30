package ru.mirea.zubarevaes.touragency.data.repository;

import java.util.ArrayList;
import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ICartRepository;

public class CartRepository implements ICartRepository {
    private List<Tour> cartItems = new ArrayList<>();

    public void addTourToCart(Tour tour) {
        cartItems.add(tour);
    }

    public List<Tour> getCartItems() {
        return cartItems;
    }

    public void removeTourFromCart(Tour tour) {
        cartItems.remove(tour);
    }
}
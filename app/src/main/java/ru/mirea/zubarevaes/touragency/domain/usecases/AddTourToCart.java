package ru.mirea.zubarevaes.touragency.domain.usecases;

import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ICartRepository;

public class AddTourToCart {
    private ICartRepository cartRepository;

    public AddTourToCart(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void execute(Tour tour) {
        cartRepository.addTourToCart(tour);
    }
}
package ru.mirea.zubarevaes.touragency.domain.usecases;

import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.repository.ICartRepository;

public class GetCartItems {
    private ICartRepository cartRepository;

    public GetCartItems(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Tour> execute() {
        return cartRepository.getCartItems();
    }
}

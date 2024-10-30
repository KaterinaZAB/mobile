package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import java.util.List;

import ru.mirea.zubarevaes.touragency.data.repository.CartRepository;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.usecases.AddTourToCart;
import ru.mirea.zubarevaes.touragency.domain.usecases.GetCartItems;

public class CartViewModel extends ViewModel {
    private List<Tour> cartItems;

    public List<Tour> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Tour> cartItems) {
        this.cartItems = cartItems;
    }


    public void loadCartItems(GetCartItems getCartItemsUseCase) {
        cartItems = getCartItemsUseCase.execute();
    }

    public void addTourToCart(Tour tour, AddTourToCart addTourToCartUseCase) {
        addTourToCartUseCase.execute(tour);

        loadCartItems(new GetCartItems(new CartRepository()));
    }
}

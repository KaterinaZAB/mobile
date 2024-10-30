package ru.mirea.zubarevaes.touragency.data.model;

import java.util.List;

import ru.mirea.zubarevaes.touragency.domain.models.Tour;

public class CartModel {
    private List<Tour> cartItems;

    public CartModel(List<Tour> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Tour> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Tour> cartItems) {
        this.cartItems = cartItems;
    }
}
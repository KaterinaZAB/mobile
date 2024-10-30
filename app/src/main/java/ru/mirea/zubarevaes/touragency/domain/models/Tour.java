package ru.mirea.zubarevaes.touragency.domain.models;

public class Tour {
    private int id;
    private String name;
    private double price;

    public Tour(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

package ru.mirea.zubarevaes.movieproject.data.model;

public class Movie {
    private int id;
    private String name;
    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
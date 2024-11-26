package ru.mirea.zubarevaes.domain.models;

import java.io.Serializable;

public class Tour implements Serializable {

    private int tripId;
    private String destination;
    private String name;
    private String description;
    private String departureDate;
    private String returnDate;
    private int price;
    private String travelType;
    private String accommodation;
    private double rating;

    public Tour(int tripId, String destination, String name, String description,
                String departureDate, String returnDate, int price, String travelType,
                String accommodation, double rating) {
        this.tripId = tripId;
        this.destination = destination;
        this.name = name;
        this.description = description;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.travelType = travelType;
        this.accommodation = accommodation;
        this.rating = rating;
    }

    public int getTripId() {
        return tripId;
    }

    public String getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public int getPrice() {
        return price;
    }

    public String getTravelType() {
        return travelType;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public double getRating() {
        return rating;
    }
}

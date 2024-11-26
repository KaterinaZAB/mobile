package ru.mirea.zubarevaes.data.repository.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteTourEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public int tripId;

    @NonNull
    public String destination;

    @NonNull
    public String name;

    public String description;

    @NonNull
    public String departureDate;

    public String returnDate;

    public int price;

    public String travelType;

    public String accommodation;

    public double rating;

    public FavoriteTourEntity(@NonNull int tripId, @NonNull String destination, @NonNull String name,
                      String description, @NonNull String departureDate, String returnDate,
                      int price, String travelType, String accommodation, double rating) {
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

    // Optional: Include getters if needed
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

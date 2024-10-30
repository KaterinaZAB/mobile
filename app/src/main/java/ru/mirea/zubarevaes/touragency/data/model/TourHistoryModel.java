package ru.mirea.zubarevaes.touragency.data.model;

import java.util.List;

import ru.mirea.zubarevaes.touragency.domain.models.Tour;

public class TourHistoryModel {
    private List<Tour> tourHistory;

    public TourHistoryModel(List<Tour> tourHistory) {
        this.tourHistory = tourHistory;
    }

    public List<Tour> getTourHistory() {
        return tourHistory;
    }

    public void setTourHistory(List<Tour> tourHistory) {
        this.tourHistory = tourHistory;
    }
}
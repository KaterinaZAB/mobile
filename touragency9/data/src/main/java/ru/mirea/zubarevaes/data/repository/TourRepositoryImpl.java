package ru.mirea.zubarevaes.data.repository;


import ru.mirea.zubarevaes.data.repository.db.FavoriteTourDao;
import ru.mirea.zubarevaes.data.repository.db.FavoriteTourEntity;
import ru.mirea.zubarevaes.data.repository.networkAPI.NetworkApi;
import ru.mirea.zubarevaes.domain.models.Tour;
import ru.mirea.zubarevaes.domain.repository.ITourRepository;

import java.util.ArrayList;
import java.util.List;

public class TourRepositoryImpl implements ITourRepository {
    private List<Tour> tours;
    private FavoriteTourDao favoriteTourDao;
    private NetworkApi networkApi;

    public TourRepositoryImpl(FavoriteTourDao favoriteTourDao) {
        this.tours = new ArrayList<>();
        this.favoriteTourDao = favoriteTourDao;
        networkApi = new NetworkApi();
        loadTours();
    }

    private void loadTours() {
        tours = networkApi.getAllTours();
    }


    @Override
    public List<Tour> getAllTours() {
        return tours;
    }

    @Override
    public Tour getTourById(int id) {
        return null;
        //return new Tour(id, "Sample Tour", 1000);
    }

    @Override
    public List<Tour> getFavoriteTours() {
        List<FavoriteTourEntity> favoriteTourEntityList = favoriteTourDao.getAllFavoriteTours();
        List<Tour> tourList = new ArrayList<>();

        for (FavoriteTourEntity favoriteEntity : favoriteTourEntityList) {
            tourList.add(new Tour(
                    favoriteEntity.tripId,             // tripId
                    favoriteEntity.getDestination(),    // destination
                    favoriteEntity.getName(),            // name
                    favoriteEntity.getDescription(),    // description
                    favoriteEntity.getDepartureDate(),  // departureDate
                    favoriteEntity.getReturnDate(),     // returnDate
                    favoriteEntity.getPrice(),          // price
                    favoriteEntity.getTravelType(),     // travelType
                    favoriteEntity.getAccommodation(),  // accommodation
                    favoriteEntity.getRating()          // rating
            ));
        }

        return tourList;
    }
}


package ru.mirea.zubarevaes.data.repository.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteTourDao {
    @Query("SELECT * FROM FavoriteTourEntity")
    List<FavoriteTourEntity> getAllFavoriteTours();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteTour(FavoriteTourEntity favoriteTour);

    @Query("DELETE FROM FavoriteTourEntity WHERE name = :tourName")
    void deleteFavoriteTour(String tourName);
}

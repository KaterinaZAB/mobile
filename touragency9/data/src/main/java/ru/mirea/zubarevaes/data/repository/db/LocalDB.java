package ru.mirea.zubarevaes.data.repository.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteTourEntity.class}, version = 1)

public abstract class LocalDB extends RoomDatabase {
    public abstract FavoriteTourDao favoriteTourDao();
}

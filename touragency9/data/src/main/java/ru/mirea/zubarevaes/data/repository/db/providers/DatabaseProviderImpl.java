package ru.mirea.zubarevaes.data.repository.db.providers;

import android.content.Context;

import androidx.room.Room;

import ru.mirea.zubarevaes.data.repository.db.FavoriteTourDao;
import ru.mirea.zubarevaes.data.repository.db.LocalDB;

public class DatabaseProviderImpl implements DatabaseProvider{
    private LocalDB databaseInstance;

    public DatabaseProviderImpl(Context context){
            databaseInstance = Room.databaseBuilder(context, LocalDB.class, "database")
                    .allowMainThreadQueries()
                    .build();
    }
    @Override
    public LocalDB getDatabaseInstance() {
        return databaseInstance;
    }

    @Override
    public FavoriteTourDao getFavoriteTourDao() {
        return databaseInstance.favoriteTourDao();
    }
}

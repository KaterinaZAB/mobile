package ru.mirea.zubarevaes.data.repository.db.providers;

import ru.mirea.zubarevaes.data.repository.db.FavoriteTourDao;
import ru.mirea.zubarevaes.data.repository.db.LocalDB;

public interface DatabaseProvider {
    LocalDB getDatabaseInstance();
    FavoriteTourDao getFavoriteTourDao();
}

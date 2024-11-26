package ru.mirea.zubarevaes.touragency.presentation;

import android.app.Application;

import ru.mirea.zubarevaes.data.repository.db.providers.DatabaseProvider;
import ru.mirea.zubarevaes.data.repository.db.providers.DatabaseProviderImpl;

public class App extends Application {
    public static App instance;
    private DatabaseProvider databaseProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        databaseProvider = new DatabaseProviderImpl(this);
    }

    public static App getInstance(){
        return instance;
    }

    public DatabaseProvider getDatabaseProvider(){
        return databaseProvider;
    }
}

package ru.mirea.zubarevaes.a4employeedb;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private AppDatabase database;

    // в классе производится инициализация базы данных в методе «onCreate»
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static App getInstance() {
        return instance;
    }
    public AppDatabase getDatabase() {
        return database;
    }
}
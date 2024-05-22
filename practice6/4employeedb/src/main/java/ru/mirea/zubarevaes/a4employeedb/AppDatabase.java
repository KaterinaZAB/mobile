package ru.mirea.zubarevaes.a4employeedb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//создание абстрактного класса базы данных,
//наследуемого от «RoomDatabase» и отвечающего за ведение самой базы данных и
//предоставление экземпляров DAO
@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}

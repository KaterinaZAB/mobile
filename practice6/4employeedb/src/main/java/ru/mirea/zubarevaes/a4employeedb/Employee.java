package ru.mirea.zubarevaes.a4employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//класс, на основании которого будет
//создана таблица базы данных
@Entity
public class Employee { //сотрудники
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String nameHero;
    public String power;
}


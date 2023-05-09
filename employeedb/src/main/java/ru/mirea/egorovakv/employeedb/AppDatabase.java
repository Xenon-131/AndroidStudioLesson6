package ru.mirea.egorovakv.employeedb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.egorovakv.employeedb.Hero;

@Database(entities = {Hero.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HeroDao heroDao();
}
package ru.mirea.egorovakv.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.mirea.egorovakv.employeedb.Hero;

@Dao
public interface HeroDao {
    @Query("SELECT * FROM hero")
    List<Hero> getAll();
    @Query("SELECT * FROM hero WHERE id = :id")
    Hero getById(long id);
    @Insert
    void insert(Hero hero);
    @Update
    void update(Hero hero);
    @Delete
    void delete(Hero hero);
}

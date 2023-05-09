package ru.mirea.egorovakv.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity : DB ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        HeroDao heroDao = db.heroDao();
        Hero hero = new Hero();
        hero.id = 1;
        hero.name = "John Smith";
        hero.superName = "Black Dragon";
        hero.power = "Ice";
        hero.numOfVictories = 18;
        // запись сотрудников в базу
        heroDao.insert(hero);
        hero.id = 2;
        hero.name = "Sergey Plushkin";
        hero.superName = "Fuzz";
        hero.power = "telekinesis";
        hero.numOfVictories = 27;
        heroDao.insert(hero);
        // Загрузка всех работников
        List<Hero> heroes = heroDao.getAll();
        for (int i =0; i<heroes.size();i++) {
            hero = heroes.get(i);
            Log.d(TAG+"all heroes", hero.id+ " " + hero.name + " " + hero.superName + " " + hero.power + " " + hero.numOfVictories);
        }

        hero = heroDao.getById(1);
        hero.numOfVictories += 3;
        heroDao.update(hero);
        Log.d(TAG+"updated hero", hero.id+ " " + hero.name + " " + hero.superName + " " + hero.power + " " + hero.numOfVictories);



    }
}
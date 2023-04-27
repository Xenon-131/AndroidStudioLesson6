package ru.mirea.egorovakv.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.egorovakv.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    SharedPreferences sharedPref =
            getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String group = sharedPref.getString("GROUP ", "BSBO-00-00");
        String number = String.valueOf(sharedPref.getInt("NUMBER", 0));
        String film = sharedPref.getString("FAV_FILM ", "Film");
        binding.editTextGroup.setText(group);
        binding.editTextNumber.setText(number);
        binding.editTextFilm.setText(film);


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("GROUP", binding.editTextGroup.getText().toString());
                editor.putInt("NUMBER", Integer.parseInt(binding.editTextNumber.getText().toString()));
                editor.putString("FAV_FILM", binding.editTextFilm.toString());
                editor.apply();
            }
        });


    }
}
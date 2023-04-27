package ru.mirea.egorovakv.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.view.View;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.egorovakv.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  SharedPreferences secureSharedPreferences = null;
  private ActivityMainBinding binding;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());

      KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
      try {
        String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        secureSharedPreferences = EncryptedSharedPreferences.create(
                "secret_shared_prefs",
                mainKeyAlias,
                getBaseContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
        String result	=	secureSharedPreferences.getString("secure poet","Poet P.");
        String imageName = secureSharedPreferences.getString("secure poet image","fet");
        binding.imageView.setImageResource(getResources().getIdentifier(imageName,"drawable",getPackageName()));

        binding.editTextPoet.setText(result);
        binding.editTextImageName.setText(imageName);
      } catch (GeneralSecurityException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      binding.button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          secureSharedPreferences.edit().putString("secure poet", binding.editTextPoet.getText().toString());
          secureSharedPreferences.edit().putString("secure poet image", binding.editTextImageName.getText().toString());
          secureSharedPreferences.edit().apply();
          binding.imageView.setImageResource(getResources().getIdentifier(binding.editTextImageName.getText().toString(),
                  "drawable",getPackageName()));
        }
      });
  }
}
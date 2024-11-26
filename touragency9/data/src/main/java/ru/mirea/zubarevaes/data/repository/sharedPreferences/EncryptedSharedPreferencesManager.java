package ru.mirea.zubarevaes.data.repository.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

//хранение информации о пользователе
public class EncryptedSharedPreferencesManager {
    private static String FILENAME = "userData";
    private SharedPreferences sharedPreferences;

    public EncryptedSharedPreferencesManager(Context context) {
        try {
            Log.d("EncryptedPrefs", "Initializing secure shared preferences");
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secure_prefs",
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            Log.e("EncryptedPrefs", "Failed to initialize encrypted preferences", e);
            throw new RuntimeException("Ошибка при создании защищённого файла настроек: ", e);
        }
    }

    public void saveUser(String email, String password){
        sharedPreferences.edit()
                .putString("EMAIL", email)
                .putString("PASSWORD", password)
                .apply();
    }

    public String getEmail(){
        return sharedPreferences.getString("EMAIL", null);
    }

    public String getPassword(){
        return sharedPreferences.getString("PASSWORD", null);
    }

    public void clearUser(){
        sharedPreferences.edit().clear().apply();
    }
}

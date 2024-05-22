//отображение имени любимого поэта и его фотографии или рисунка.

package ru.mirea.zubarevaes.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.zubarevaes.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //три основных части:
        //«MasterKeys», «EncryptedSharedPreferences» и «EncryptedFile».

        // создание параметров генерации мастер-ключа для AES256 GCM
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        try {
            // получение или создание основного ключевого псевдонима
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

            //EncryptedSharedPreferences позволяет сохранять и читать значения, выполнять
            //шифрование и расшифровывание данных внутри него реализации

            // создание зашифрованных общих предпочтений (SharedPreferences)
            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs", // имя зашифрованных общих предпочтений
                    mainKeyAlias, // основной ключевой псевдоним
                    getBaseContext(), // контекст приложения
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // алгоритм шифрования ключа предпочтений
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // алгоритм шифрования значения предпочтений
            );

            // сохранение строки "Самуил Маршак" в зашифрованных общих предпочтениях
            secureSharedPreferences.edit().putString("secure", "Самуил Маршак").apply();

            // установка текста в TextView из зашифрованных общих предпочтений
            binding.textView1.setText(secureSharedPreferences.getString("secure", "not found"));
        } catch (GeneralSecurityException | IOException e) {
            // в случае возникновения ошибки выводим стек вызовов исключения
            throw new RuntimeException(e);
        }

    }
}
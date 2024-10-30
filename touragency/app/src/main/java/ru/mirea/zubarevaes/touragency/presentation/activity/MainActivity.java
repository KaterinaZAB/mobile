package ru.mirea.zubarevaes.touragency.presentation;

import ru.mirea.zubarevaes.touragency.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button toursButton;
    private Button valutesButton;
    private Button loginButton;
    private Button recognitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация кнопок
        toursButton = findViewById(R.id.toursButton);
        valutesButton = findViewById(R.id.valutesButton);
        loginButton = findViewById(R.id.loginButton);
        recognitionButton = findViewById(R.id.recognitionButton);

        // Обработчики нажатий на кнопки

        // Переход к списку туров
        toursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь будет переход на экран со списком туров
                Toast.makeText(MainActivity.this, "Opening Tours", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, ToursActivity.class);
                // startActivity(intent);
            }
        });

        // Переход к курсам валют
        valutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь будет переход на экран с курсами валют
                Toast.makeText(MainActivity.this, "Opening Exchange Rates", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, ValutesActivity.class);
                // startActivity(intent);
            }
        });

        // Переход к странице авторизации
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь будет переход на экран авторизации
                Toast.makeText(MainActivity.this, "Opening Login", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                // startActivity(intent);
            }
        });

        // Переход к распознаванию текста
        recognitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь будет запуск функции распознавания текста
                Toast.makeText(MainActivity.this, "Starting Text Recognition", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, RecognitionActivity.class);
                // startActivity(intent);
            }
        });
    }
}

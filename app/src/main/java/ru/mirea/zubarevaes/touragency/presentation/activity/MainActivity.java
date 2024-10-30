package ru.mirea.zubarevaes.touragency.presentation.activity;

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
    private Button profileButton;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toursButton = findViewById(R.id.toursButton);
        valutesButton = findViewById(R.id.valutesButton);
        loginButton = findViewById(R.id.loginButton);
        recognitionButton = findViewById(R.id.recognitionButton);
        profileButton = findViewById(R.id.profileButton);
        cartButton = findViewById(R.id.cartButton);

        toursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Открыть список туров", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, ToursActivity.class);
                // startActivity(intent);
            }
        });

        valutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Открыть курсы валют", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, ValutesActivity.class);
                // startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Открыть авторизацию", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                // startActivity(intent);
            }
        });

        recognitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Запустить функцию распознавания текста", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, RecognitionActivity.class);
                // startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}

//всплывающие подсказки

package ru.mirea.zubarevaes.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //подсчет символов в строке
    public void showToast(View view) {
        EditText inputEditText = findViewById(R.id.editTextText);
        String inputText = inputEditText.getText().toString();
        int charCount = inputText.length();
        String message = "СТУДЕНТ № 11 ГРУППА 11 Количество символов - " + charCount;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
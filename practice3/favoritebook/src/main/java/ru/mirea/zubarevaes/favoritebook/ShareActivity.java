package ru.mirea.zubarevaes.favoritebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class ShareActivity extends AppCompatActivity {
    private Button sendBtn;
    private EditText editTextBookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        sendBtn = findViewById(R.id.sendMessageBack);
        editTextBookTitle = findViewById(R.id.editTextBookTitle);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение данных из EditText
                String userBook = editTextBookTitle.getText().toString();

                // Создание нового Intent для возврата данных в MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.USER_MESSAGE, userBook);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}

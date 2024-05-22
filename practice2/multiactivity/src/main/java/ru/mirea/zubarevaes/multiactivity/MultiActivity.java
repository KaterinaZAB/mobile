// MultiActivity.java
package ru.mirea.zubarevaes.multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Log.d("MultiActivity", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MultiActivity", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MultiActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MultiActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MultiActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MultiActivity", "onDestroy()");
    }

    public void onClickNewActivity(View view) {
        Intent intent = new Intent(MultiActivity.this, SecondActivity.class);//явные
        intent.putExtra("key", "MIREA - ЗУБАРЕВА ЕКАТЕРИНА СЕРГЕЕВНА");
        startActivity(intent);
    }
}

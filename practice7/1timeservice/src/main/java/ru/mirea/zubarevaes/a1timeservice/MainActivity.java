package ru.mirea.zubarevaes.a1timeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;

import ru.mirea.zubarevaes.a1timeservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String host = "time.nist.gov";
    private final int port= 13;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTimeTask timeTask = new GetTimeTask();
                timeTask.execute();
            }
        });
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {


        //выполняются основные вычисления и который возвращает результат
        //вычислений в метод «onPostExecute»
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // игнорируем первую строку
                timeResult = reader.readLine(); // считываем вторую строку
                Log.d("socket activity" ,timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //binding.textView.setText(result);
            //добавляет "Дата: " к result с 6-го по 13-й индекс (индексация начинается с 0).
            binding.textView.setText("Дата: " + result.substring(6, 14) +
                    "\nВремя: " + result.substring(15, 23));
            //добавляет "Время: " к result с 15-го по 22-й индекс
        }
    }

}
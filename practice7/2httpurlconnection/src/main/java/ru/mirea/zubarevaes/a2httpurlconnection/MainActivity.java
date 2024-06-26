//Требуется определить внешний IP-адрес устройства
package ru.mirea.zubarevaes.a2httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.zubarevaes.a2httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Обработчик для кнопки, которая определяет внешний IP-адрес
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Проверка доступности интернет-соединения
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    // Запуск нового потока для загрузки данных о внешнем IP
                    new DownloadPageTask().execute("https://ipinfo.io/json");
                } else {
                    binding.textView.setText("Нет интернета");
                }
            }
        });

        // Обработчик для кнопки, которая получает данные о погоде
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Проверка доступности интернет-соединения
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    // Запуск нового потока для загрузки данных о погоде
                    new DownloadPageTaskWeather().execute("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true");
                } else {
                    binding.textView.setText("Нет интернета");
                }
            }
        });
    }

    // Поток для загрузки данных о внешнем IP
    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Отображение сообщения о загрузке данных
            binding.textView.setText("Загружаем...");
            binding.textView2.setText("Загружаем...");
            binding.textView3.setText("Загружаем...");
            binding.textView4.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                // Вызов метода для загрузки данных о внешнем IP
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Обработка полученных данных и отображение на экране
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                String ip = responseJson.getString("ip");
                Log.d(MainActivity.class.getSimpleName(), "IP: " + ip);
                binding.textView.setText("Город: " + responseJson.getString("city"));
                binding.textView2.setText("Регион: " + responseJson.getString("region"));
                binding.textView3.setText("Ip: " + responseJson.getString("ip"));
                binding.textView4.setText("Координаты: " + responseJson.getString("loc"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }

    // Поток для загрузки данных о погоде
    private class DownloadPageTaskWeather extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Отображение сообщения о загрузке данных
            binding.textView5.setText("Загружаем...");
            binding.textView6.setText("Загружаем...");
            binding.textView7.setText("Загружаем...");
            binding.textView8.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                // Вызов метода для загрузки данных о погоде
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Обработка полученных данных и отображение на экране
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                JSONObject weatherJSON = responseJson.getJSONObject("current_weather");
                binding.textView5.setText("Температура: " + weatherJSON.getString("temperature"));
                binding.textView6.setText("Скорость ветра: " + weatherJSON.getString("windspeed"));
                binding.textView7.setText("Направление ветра: " + weatherJSON.getString("winddirection"));
                binding.textView8.setText("Время: " + weatherJSON.getString("time"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }

    // Метод для загрузки данных по указанному URL
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //получение и инициализация объекта
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Чтение ответа и сохранение данных
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}

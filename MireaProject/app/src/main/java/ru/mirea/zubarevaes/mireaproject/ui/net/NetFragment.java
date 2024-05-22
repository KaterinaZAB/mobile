package ru.mirea.zubarevaes.mireaproject.ui.net;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.zubarevaes.mireaproject.R;

public class NetFragment extends Fragment {

    private TextView infoTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_net, container, false);// создаем представление фрагмента
        // находим TextView для вывода информации
        infoTextView = rootView.findViewById(R.id.infoTextView);
        // запускаем загрузку данных
        fetchData();
        return rootView;
    }

    private void fetchData() {
        // создаем и запускаем асинхронную задачу для загрузки данных
        new FetchDataTask().execute("https://jsonplaceholder.typicode.com/todos");
    }

    // класс для асинхронной загрузки данных
    private class FetchDataTask extends AsyncTask<String, Void, String> {

        // метод, выполняющийся в фоновом потоке для загрузки данных
        @Override
        protected String doInBackground(String... strings) {
            String data = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                data = stringBuilder.toString();

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        // метод, выполняющийся после загрузки данных
        @Override
        protected void onPostExecute(String result) {
            try {
                // парсим полученные данные в формате JSON
                JSONArray todosArray = new JSONArray(result);
                // создаем строку для отображения данных
                StringBuilder stringBuilder = new StringBuilder();
                // проходимся по каждому элементу массива
                for (int i = 0; i < todosArray.length(); i++) {
                    JSONObject todoObject = todosArray.getJSONObject(i);



                    // извлекаем данные
                    String userId = todoObject.getString("userId");
                    String id = todoObject.getString("id");
                    String title = todoObject.getString("title");
                    boolean completed = todoObject.getBoolean("completed");


                    stringBuilder.append("UserID: ").append(userId).append("\n")// формирую строку с информацией о задаче
                            .append("ID: ").append(id).append("\n")
                            .append("Title: ").append(title).append("\n")
                            .append("Completed: ").append(completed).append("\n\n");
                }

                infoTextView.setText(stringBuilder.toString());// выводим данные в TextView
            } catch (JSONException e) {
                e.printStackTrace();
                infoTextView.setText("Error: " + e.getMessage());
            }
            super.onPostExecute(result);
        }
    }
}

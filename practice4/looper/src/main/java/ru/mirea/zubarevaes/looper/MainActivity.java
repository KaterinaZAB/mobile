//использование Looper и Handler для организации коммуникации между основным потоком и фоновым потоком
package ru.mirea.zubarevaes.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import ru.mirea.zubarevaes.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // отвечает за организацию очереди, создание обработчика данных
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task	execute.	This	is	result:	" + msg.getData().getString("result"));
            }
        };

        //запуск потока в котором будет выполняться цикл обработки сообщений.
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        //создаётся новый поток - инициализация переменных и создание
        //объектов будут уже идти параллельно с теми вызовами, которые установлены
        //в следующих строчках после команды «myLooper.start»

        binding.buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageStr = binding.age.getText().toString();
                String profession = binding.work.getText().toString();

                int age;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException e) {
                    age = 0;
                }


                int delay = age * 1000; // convert years to milliseconds

                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("result", "Age: " + age + ", Profession: " + profession);
                msg.setData(bundle);

                myLooper.mHandler.sendMessageDelayed(msg, delay); //отправляет смс с полученными данными  в фоновый поток с задержкой, зависящей от возраста
            }
        });
    }
}
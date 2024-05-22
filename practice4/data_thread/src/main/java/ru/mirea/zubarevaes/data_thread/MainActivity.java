//выполнения кода в основном потоке (UI потоке) из другого потока

package ru.mirea.zubarevaes.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.zubarevaes.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // используется для привязки макета активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());//Устанавливается созданный макет
        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.ttext.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.ttext.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.ttext.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1); //Этот метод позволяет запускать код, который должен выполняться в основном (UI)
                    // потоке, из другого потока.

                    TimeUnit.SECONDS.sleep(1);
                    binding.ttext.postDelayed(runn3, 2000); // Этот метод работает аналогично методу post(),
                    // за исключением того, что задача будет выполнена через определенное количество времени,
                    // указанное в миллисекундах.
                    binding.ttext.post(runn2); //Метод post() позволяет добавлять задачи в очередь выполнения
                    // для основного потока (UI потока).

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
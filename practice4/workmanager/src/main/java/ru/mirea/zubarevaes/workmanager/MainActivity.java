//приложение Android с использованием WorkManager для выполнения фоновой работы.
//
package ru.mirea.zubarevaes.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //сздается экземпляр класса OneTimeWorkRequest, который представляет одноразовую задачу работы
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .build();
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest); // с пом этого метода созданный запрос на выполнение работы
                                              // добавляется в очередь работы WorkManager
    }
}
package ru.mirea.zubarevaes.mireaproject.ui.workerBackTask;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import ru.mirea.zubarevaes.mireaproject.R;
import ru.mirea.zubarevaes.mireaproject.ui.workerBackTask.UploadWorker;

public class BackgroundTask extends Fragment {

    private CheckBox checkBox;
    private static final int PERMISSION_REQUEST_CODE = 123;

    public BackgroundTask() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_background_task, container, false);

        checkBox = view.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // При нажатии на CheckBox, проверяем разрешения перед запуском фоновой задачи
                checkPermissions();
            }
        });

        return view;
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.FOREGROUND_SERVICE) == PackageManager.PERMISSION_GRANTED) {
            // Если разрешение уже предоставлено, запускаем фоновую задачу
            startBackgroundTask();
        } else {
            // Если разрешение не предоставлено, запрашиваем его у пользователя
            requestPermissions(new String[]{Manifest.permission.FOREGROUND_SERVICE}, PERMISSION_REQUEST_CODE);
        }
    }

    private void startBackgroundTask() {
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .build();
        WorkManager
                .getInstance(requireContext())
                .enqueue(uploadWorkRequest);
    }
}

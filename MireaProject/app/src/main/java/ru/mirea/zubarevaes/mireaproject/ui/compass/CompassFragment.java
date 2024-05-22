package ru.mirea.zubarevaes.mireaproject.ui.compass;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import ru.mirea.zubarevaes.mireaproject.R;

public class CompassFragment extends Fragment implements SensorEventListener {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private ImageView compassImageView;
    private TextView directionTextView;
    private SensorManager sensorManager;
    private Sensor magnetometerSensor;

    public CompassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        compassImageView = view.findViewById(R.id.compassImageView);
        directionTextView = view.findViewById(R.id.directionTextView);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // проверка и запрос разрешения
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        } else {
            // разрешение уже предоставлено, можно начать использовать датчик
            startUsingCompass();
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // разрешение получено, можно начать использование датчика
                startUsingCompass();
            } else {
                // разрешение не получено, выводим сообщение об ошибке
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startUsingCompass() {
        // регистрирую слушателя событий датчика магнитного поля
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onResume() {
        super.onResume();
        // запускаю использование датчика при возобновлении фрагмента
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startUsingCompass();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // останавливаю использование датчика при приостановке фрагмента
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // обработка данных с датчика магнитного поля
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float azimuth = (float) Math.toDegrees(event.values[0]);
            if (azimuth < 0) {
                azimuth += 360;
            }
            compassImageView.setRotation(-azimuth);
            String direction;
            if (azimuth >= 315 || azimuth < 45) {
                direction = "North";
            } else if (azimuth >= 45 && azimuth < 135) {
                direction = "East";
            } else if (azimuth >= 135 && azimuth < 225) {
                direction = "South";
            } else {
                direction = "West";
            }
            directionTextView.setText("Direction: " + direction);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // обработка изменения точности датчика
    }
}

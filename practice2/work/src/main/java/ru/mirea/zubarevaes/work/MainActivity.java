package ru.mirea.zubarevaes.work;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTimePickerDialog(View view) {
        MyTimeDialogFragment timePickerFragment = new MyTimeDialogFragment();
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // DatePickerDialog
    public void onDatePickerDialog(View view) {
        MyDateDialogFragment datePickerFragment = new MyDateDialogFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // ProgressDialog
    public void onProgressDialog(View view) {
        MyProgressDialogFragment myProgressDialogFragment = new MyProgressDialogFragment();
        myProgressDialogFragment.show(getSupportFragmentManager(), "progressDialog");
    }
}

package ru.mirea.zubarevaes.a4employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.mirea.zubarevaes.a4employeedb.databinding.ActivityMainBinding;
//«/data/имя_пакета/databases/имя_базы.db»
public class MainActivity extends AppCompatActivity {

    private EditText editTextName;

    private EditText editTextPower;

    private EditText editTextID;
    private Button buttonInsert;
    private Button buttonGet;
    private TextView textView;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextPower = binding.editTextPower;
        editTextName = binding.editTextName;
        editTextID = binding.editTextId;
        textView = binding.textView;
        buttonInsert = binding.buttonInsert;
        buttonGet = binding.buttonGet;

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                String name = editTextName.getText().toString();
                String power = editTextPower.getText().toString();
                if (!name.isEmpty() && !power.isEmpty()) {
                    employee.nameHero = name;
                    employee.power = power;
                    //запись героя в базу
                    employeeDao.insert(employee);
                    Toast.makeText(MainActivity.this, "Добавлено", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = editTextID.getText().toString();
                if (i != null) {
                    int id = Integer.parseInt(i);
                    Employee employee = new Employee();
                    employee = employeeDao.getById(id);
                    if (employee != null) {
                        textView.setText("Имя героя: " + employee.nameHero + "\nЕго сила: " + employee.power);
                    } else {
                        Toast.makeText(MainActivity.this, "Такой записи нет", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
//
// при нажатии кнопки выполняется асинхронная операция в новом потоке.
//Посчитать в фоновом потоке среднее количество пар в день за период одного месяца.
//
package ru.mirea.zubarevaes.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.zubarevaes.thread.databinding.ActivityMainBinding;

public	class MainActivity extends AppCompatActivity	{
    private	ActivityMainBinding	binding;

    private	int	counter	= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCount.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void onClick(View v)	{
                //после нажатия на кнопку, создается новый потк
                new	Thread(new Runnable()	{
                    public void run()	{
                        String p = binding.countPar.getText().toString();
                        String d = binding.countDays.getText().toString();
                        int a = Integer.parseInt(p) / Integer.parseInt(d);
                        //результат выожу в текстовое поле par с использованием метода runOnUiThread(),
                        // чтобы обновить пользовательский интерфейс из главного потока
                        runOnUiThread(() -> binding.par.setText(""+a));


                        int	numberThread = counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток №	%d	студентом группы № 11 номер	по " +
                                " списку №11",	numberThread, "БСБО-11-21",	-1));
                    }
                }).start();
            }
        });
    }

}

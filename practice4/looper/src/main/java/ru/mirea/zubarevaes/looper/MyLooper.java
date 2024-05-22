package ru.mirea.zubarevaes.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import android.os.Handler;
import java.util.logging.LogRecord;

public	class MyLooper extends Thread{
    public	Handler	mHandler;
    private	Handler	mainHandler;
    public MyLooper(Handler mainThreadHandler){
        mainHandler=mainThreadHandler;
    }

    public void run(){
        //в бесконечном цикле  проверяет, нет ли в ней новых задач для потока
        Looper.prepare();

        //обрабатывает сообщения, приходящие в «MessageQueue»
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String result = msg.getData().getString("result");
                if (result != null) {
                    Log.d("MyLooper", "задача выполнена, результат: " + result);
                } else {
                    Log.d("MyLooper", "задача выполнена, результата нет.");
                }
            }
        };
        Looper.loop(); //запуск цикла
    }




}

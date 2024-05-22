// MyLoader.java
package ru.mirea.zubarevaes.cryptoloader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

//использую AsyncTaskLoader для выполнения дешифрования в фоновом потоке.
public class MyLoader extends AsyncTaskLoader<String> {

    public static final String ARG_WORD = "arg_word";

    private byte[] mCipherText;

    //Для реализации собственного загрузчика, требуется создание класса
    //и переопределение методов «loadInBackground» и «onStartLoading».

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null && args.containsKey(ARG_WORD)) {
            mCipherText = args.getByteArray(ARG_WORD);
        }
    }

    //имитирует процесс дешифрования, засыпая на 2 секунды, а затем возвращая дешифрованный текст
    @Nullable
    @Override
    public String loadInBackground() {
        // Simulate loading process
        try {
            Thread.sleep(2000); // Simulating a delay of 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Return the decrypted text as a result of loading
        return new String(mCipherText); // Here should be actual decryption process
    }

    //начинает загрузку данных и вызывает forceLoad() для принудительного запуска загрузчика для обработки данных
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCipherText != null) {
            forceLoad();
        }
    }
}

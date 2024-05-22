// шифрование сообщений для быстрой
//и незаметная для пользователя загрузка данных из сети или файловой системы
package ru.mirea.zubarevaes.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import ru.mirea.zubarevaes.cryptoloader.MyLoader;

//Работа с «LoaderManager» требует
//переопределения трех методов обратного вызова интерфейса
public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private final int LoaderID = 1234;
    private SecretKey key;
    private EditText textInput;
    private TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInput = findViewById(R.id.editTextText);
        textOutput = findViewById(R.id.textView);
    }
    //«Loader» – это компонент ОС «Android», который через класс
    //«LoaderManager» связан с жизненным циклом «Activity» и «Fragment». Это
    //позволяет использовать их без опасения, что данные будут утрачены при закрытии
    //приложения или результат вернется не тому потребителю
    public void onClickButton(View view) {
        String text = textInput.getText().toString();
        key = generateKey();
        byte[] cipherText = encryptMsg(text, key);
        Bundle bundle = new Bundle();
        bundle.putByteArray(MyLoader.ARG_WORD, cipherText);
        bundle.putByteArray("key", key.getEncoded());
        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this);
    }

    //перезагружает данные в загрузчике
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    //возвращает новый загрузчик
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        throw new InvalidParameterException("Invalid loader id");
    }

    //вызывается автоматически, когда «Loader»
    //завершает загрузку данных. Загрузчик следит за поступающими данными, а
    //менеджер получает уведомление о завершении загрузки и передаёт результат
    //данному методу. Этот метод гарантировано вызывается до высвобождения
    //последних данных, которые были предоставлены этому загрузчику.
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished: " + s);
            String decryptedText = decryptMsg(s.getBytes(), key);
            textOutput.setText(decryptedText);
            Toast.makeText(this, "Decrypted Text: " + decryptedText, Toast.LENGTH_SHORT).show();
        }
    }

    //генерирует секретный ключ с использованием алгоритма AES
    public static SecretKey generateKey() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            return new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //шифрует введенный текст с использованием алгоритма AES
    public static byte[] encryptMsg(String message, SecretKey secret) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    //дешифрует зашифрованный текст
    public static String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}

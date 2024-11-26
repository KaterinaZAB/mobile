package ru.mirea.zubarevaes.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.zubarevaes.data.repository.sharedPreferences.EncryptedSharedPreferencesManager;
import ru.mirea.zubarevaes.domain.callback.AuthCallback;
import ru.mirea.zubarevaes.domain.repository.IUserRepository;

public class UserRepositoryImpl implements IUserRepository {

    private final FirebaseAuth firebaseAuth;
    private EncryptedSharedPreferencesManager preferencesManager;
    public UserRepositoryImpl(Context context) {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.preferencesManager = new EncryptedSharedPreferencesManager(context);
    }
    @Override
    public void registerUser(String email, String password, AuthCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        preferencesManager.saveUser(email, password);
                        callback.onSuccess(email);
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void signInUser(String email, String password, AuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        preferencesManager.saveUser(email, password);
                        callback.onSuccess(email);
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }


    @Override
    public void signOutUser() {
        firebaseAuth.signOut();
        preferencesManager.clearUser();
    }

    @Override
    public void autoSignIn(AuthCallback callback) {
        String email = preferencesManager.getEmail();
        String password = preferencesManager.getPassword();

        if (email != null && password != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            // вернём null, если время истечёт
            Runnable timeoutRunnable = () -> callback.onFailure("Время ожидания вышло");
            handler.postDelayed(timeoutRunnable, 3000);

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        // иначе вызовится после 3 сек
                        handler.removeCallbacks(timeoutRunnable);
                        if (task.isSuccessful()) {
                            callback.onSuccess(email);
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    });
        }
        callback.onFailure("Нет данных в хранилище");
    }
}

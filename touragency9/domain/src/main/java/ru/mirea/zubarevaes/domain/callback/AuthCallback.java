package ru.mirea.zubarevaes.domain.callback;

public interface AuthCallback {
    void onSuccess(String email);
    void onFailure(String errorMessage);
}

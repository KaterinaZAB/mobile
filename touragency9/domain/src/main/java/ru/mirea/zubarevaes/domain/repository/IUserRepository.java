package ru.mirea.zubarevaes.domain.repository;

import ru.mirea.zubarevaes.domain.callback.AuthCallback;
import ru.mirea.zubarevaes.domain.models.User;

public interface IUserRepository {
    void registerUser(String email, String password, AuthCallback callback);
    void signInUser(String email, String password, AuthCallback callback);
    void signOutUser();
    void autoSignIn(AuthCallback callback);
}
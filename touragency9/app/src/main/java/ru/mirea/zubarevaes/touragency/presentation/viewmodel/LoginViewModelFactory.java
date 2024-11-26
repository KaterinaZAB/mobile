package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zubarevaes.domain.usecases.LoginUser;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUser loginUserUseCase;

    public LoginViewModelFactory(LoginUser loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginUserUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

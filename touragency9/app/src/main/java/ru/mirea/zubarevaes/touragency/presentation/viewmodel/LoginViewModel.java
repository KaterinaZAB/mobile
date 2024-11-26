package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ru.mirea.zubarevaes.domain.callback.AuthCallback;
import ru.mirea.zubarevaes.domain.usecases.LoginUser;

public class LoginViewModel extends ViewModel {
    private final LoginUser loginUserUseCase;

    private final MutableLiveData<String> successMessage = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>(false);// LiveData для отслеживания состояния авторизации

    public LoginViewModel(LoginUser loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<String> getErrorMessage() { //неизменяемая
        return errorMessage;
    }


    public LiveData<Boolean> getIsAuthenticated() {
        return isAuthenticated;
    }

    public void register(String email, String password) {
        loginUserUseCase.executeRegister(email, password, new AuthCallback() {
            @Override
            public void onSuccess(String email) {
                successMessage.setValue("Регистрация успешна: " + email);
                isAuthenticated.setValue(true);
            }

            @Override
            public void onFailure(String errorMessage) {
                LoginViewModel.this.errorMessage.setValue("Ошибка регистрации: " + errorMessage);
            }
        });
    }

    public void login(String email, String password) {
        loginUserUseCase.executeLogin(email, password, new AuthCallback() {
            @Override
            public void onSuccess(String email) {
                successMessage.setValue("Вход успешен: " + email);
                isAuthenticated.setValue(true);
            }

            @Override
            public void onFailure(String errorMessage) {
                LoginViewModel.this.errorMessage.setValue("Ошибка входа: " + errorMessage);
            }
        });
    }


}


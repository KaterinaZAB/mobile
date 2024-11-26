package ru.mirea.zubarevaes.domain.usecases;

import ru.mirea.zubarevaes.domain.repository.IUserRepository;
import ru.mirea.zubarevaes.domain.callback.AuthCallback;

public class LoginUser {
    private IUserRepository userRepository;

    public LoginUser(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void executeLogin(String email, String password, AuthCallback callback){
        userRepository.signInUser(email, password, callback);
    }

    public void executeLogout(){
        userRepository.signOutUser();
    }

    public void executeRegister(String email, String password, AuthCallback callback){
        userRepository.registerUser(email, password, callback);
    }

    public void executeAutoSign(AuthCallback callback){
        userRepository.autoSignIn(callback);
    }
}

package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zubarevaes.domain.repository.ITourRepository;
import ru.mirea.zubarevaes.domain.usecases.LoginUser;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final ITourRepository tourRepository;
    private final LoginUser loginUser;

    public MainViewModelFactory(ITourRepository tourRepository, LoginUser loginUser) {
        this.tourRepository = tourRepository;
        this.loginUser = loginUser;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(tourRepository, loginUser);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

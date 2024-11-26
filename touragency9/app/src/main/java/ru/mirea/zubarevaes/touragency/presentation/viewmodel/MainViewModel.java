package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.zubarevaes.domain.callback.AuthCallback;
import ru.mirea.zubarevaes.domain.models.Tour;
import ru.mirea.zubarevaes.domain.repository.ITourRepository;
import ru.mirea.zubarevaes.domain.usecases.LoginUser;

public class MainViewModel extends ViewModel {
    private final ITourRepository tourRepository; // репозиторий для получения туров
    private final LoginUser loginUser;

    //MutableLiveData — это подкласс LiveData, который позволяет изменять данные извне (через метод setValue() или postValue())
    private final MutableLiveData<List<Tour>> networkTours = new MutableLiveData<>();// данные о турах, полученные из сети
    private final MutableLiveData<List<Tour>> favoriteTours = new MutableLiveData<>();
    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);


    // медиатор для объединения данных о турах
    private final MediatorLiveData<List<Tour>> mediatorTours = new MediatorLiveData<>();

    public MainViewModel(ITourRepository tourRepository, LoginUser loginUser) {
        this.tourRepository = tourRepository;
        this.loginUser = loginUser;
        checkMediatorTours();
        autoSign();
    }

    //LiveData — это класс, который предоставляет доступ к данным и позволяет наблюдать за ними
    public LiveData<List<Tour>> getNetworkTours() {
        return networkTours;
    }
    public LiveData<List<Tour>> getFavoriteTours() {
        return favoriteTours;
    }// возвращаем данные о избранных турах

    public LiveData<List<Tour>> getTours() {
        return mediatorTours;
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        userName.setValue(name);
    } // установка имени пользователя

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadAllTours() {
        networkTours.setValue(tourRepository.getAllTours());
    }

    public void loadFavoriteTours() {
        favoriteTours.setValue(tourRepository.getFavoriteTours());
    }



    // реализация мериатора, настройка медиатора для наблюдения о данных туров из сети и избранных
    public void checkMediatorTours() {
        mediatorTours.addSource(networkTours, tours -> {
            if (tours != null) {
                mediatorTours.setValue(tours);
            }
        });

        mediatorTours.addSource(favoriteTours, tours -> {
            if (tours != null) {
                mediatorTours.setValue(tours);
            }
        });
    }

    public void logout() {
        loginUser.executeLogout();
        userName.setValue("Гость");
    }

    private void autoSign() {
        loginUser.executeAutoSign(new AuthCallback() {
            @Override
            public void onSuccess(String email) {
                userName.setValue(email);
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(String errorMessage) {
                userName.setValue("Гость");
                isLoading.setValue(false);
            }
        });
    }
}

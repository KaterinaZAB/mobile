package ru.mirea.zubarevaes.touragency.presentation.activity;

import ru.mirea.zubarevaes.data.repository.db.FavoriteTourDao;
import ru.mirea.zubarevaes.data.repository.TourRepositoryImpl;
import ru.mirea.zubarevaes.data.repository.UserRepositoryImpl;
import ru.mirea.zubarevaes.domain.models.Tour;
import ru.mirea.zubarevaes.domain.repository.ITourRepository;
import ru.mirea.zubarevaes.domain.repository.IUserRepository;
import ru.mirea.zubarevaes.domain.usecases.LoginUser;
import ru.mirea.zubarevaes.touragency.databinding.ActivityMainBinding;
import ru.mirea.zubarevaes.touragency.presentation.App;
import ru.mirea.zubarevaes.touragency.presentation.adapters.TourAdapter;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.MainViewModel;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.MainViewModelFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private TourAdapter tourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FavoriteTourDao favoriteTourDao = App.getInstance().getDatabaseProvider().getFavoriteTourDao();
        ITourRepository tourRepository = new TourRepositoryImpl(favoriteTourDao);
        IUserRepository userRepository = new UserRepositoryImpl(this);
        LoginUser loginUser = new LoginUser(userRepository);

        
        MainViewModelFactory factory = new MainViewModelFactory(tourRepository, loginUser);//для созддания зависимостей и передачи в модель(тк она не должна ни от кого зависеть)
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        binding.recyclerTours.setLayoutManager(new LinearLayoutManager(this));
        setupObservers();
        setupListeners();

        mainViewModel.loadAllTours();
    }

    private void setupObservers() {
        mainViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.imageLoading.setVisibility(View.VISIBLE);
            } else {
                binding.imageLoading.setVisibility(View.GONE);
            }
        });

        mainViewModel.getUserName().observe(this, userName -> {
            if ("Гость".equals(userName)) {
                binding.textNameUser.setText("Гость");
                binding.buttonLogin.setVisibility(View.VISIBLE);
                binding.buttonLogout.setVisibility(View.GONE);
            } else {
                binding.textNameUser.setText(userName);
                binding.buttonLogin.setVisibility(View.GONE);
                binding.buttonLogout.setVisibility(View.VISIBLE);
            }
        });

        mainViewModel.getTours().observe(this, tours -> {
            if (tours != null && !tours.isEmpty()) {
                binding.recyclerTours.setVisibility(View.VISIBLE);
                setAdapterData(tours);
            } else {
                binding.recyclerTours.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupListeners() {
        binding.buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, 1);
        });

        binding.buttonLogout.setOnClickListener(v -> {
            mainViewModel.logout();
            Toast.makeText(this, "Выход выполнен", Toast.LENGTH_SHORT).show();
        });

        binding.buttonUpdateList.setOnClickListener(v -> mainViewModel.loadAllTours());

        binding.buttonFavoriteTour.setOnClickListener(v -> mainViewModel.loadFavoriteTours());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String email = data.getStringExtra("EMAIL");
            mainViewModel.setUserName(email);
        }
    }

    private void setAdapterData(List<Tour> tourList) {
        tourAdapter = new TourAdapter(tourList, this::openTourDetailsFragment);
        binding.recyclerTours.setAdapter(tourAdapter);
    }

    private void openTourDetailsFragment(Tour tour) {
        // реализация открытия деталей тура
    }

}

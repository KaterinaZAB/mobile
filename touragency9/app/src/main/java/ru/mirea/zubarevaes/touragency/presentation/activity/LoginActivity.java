package ru.mirea.zubarevaes.touragency.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zubarevaes.data.repository.UserRepositoryImpl;
import ru.mirea.zubarevaes.domain.callback.AuthCallback;
import ru.mirea.zubarevaes.domain.repository.IUserRepository;
import ru.mirea.zubarevaes.domain.usecases.LoginUser;
import ru.mirea.zubarevaes.touragency.databinding.ActivityLoginBinding;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.LoginViewModel;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        IUserRepository userRepository = new UserRepositoryImpl(this);
        LoginUser loginUserUseCase = new LoginUser(userRepository); // создание use case для авторизации


        LoginViewModelFactory factory = new LoginViewModelFactory(loginUserUseCase); // фабрика для viewmodel
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class); // получение viewmodel

        observeViewModel(); // подписка на изменения данных в viewmodel
        setupListeners(); // настройка обработчиков событий
    }

    private void setupListeners() {
        binding.buttonReg.setOnClickListener(v -> registration());
        binding.buttonAuth.setOnClickListener(v -> login());
        binding.buttonBack.setOnClickListener(v -> finish());
    }

    private void observeViewModel() {
        loginViewModel.getSuccessMessage().observe(this, successMessage -> {
            Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(); // создание интента для передачи результата
            intent.putExtra("EMAIL", binding.emailEditText.getText().toString().toLowerCase().trim());// добавление email в интент
            setResult(RESULT_OK, intent);
            finish();
        });
        // наблюдатель за смс об ошибке
        loginViewModel.getErrorMessage().observe(this, errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        );
    }

    private void registration() {
        String email = binding.emailEditText.getText().toString().toLowerCase().trim();
        String password = binding.passwordEditText.getText().toString().toLowerCase().trim();

        if (!validateInputs(email, password)) {
            return;
        }

        loginViewModel.register(email, password);
    }

    private void login() {
        String email = binding.emailEditText.getText().toString().toLowerCase().trim();
        String password = binding.passwordEditText.getText().toString().toLowerCase().trim();

        if (!validateInputs(email, password)) {
            return;
        }

        loginViewModel.login(email, password);
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

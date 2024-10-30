package ru.mirea.zubarevaes.touragency.presentation.activity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.mirea.zubarevaes.touragency.R;
import ru.mirea.zubarevaes.touragency.data.repository.TourHistoryRepository;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.ProfileViewModel;
import ru.mirea.zubarevaes.touragency.domain.usecases.GetTourHistory;
import ru.mirea.zubarevaes.touragency.presentation.adapters.TourAdapter;

public class ProfileActivity extends AppCompatActivity {
    private ProfileViewModel profileViewModel;
    private RecyclerView recyclerView;
    private TourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView = new TextView(this);
        textView.setText("Здесь будет история поездок пользователя");
        setContentView(textView);

        // Инициализация ViewModel
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        // RecyclerView для отображения истории поездок
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Предположим, что у нас есть адаптер для туров
        adapter = new TourAdapter(profileViewModel.getTourHistory());
        recyclerView.setAdapter(adapter);

        // Загрузка истории туров (например, для пользователя с userId = 1)
        profileViewModel.loadTourHistory(1, new GetTourHistory(new TourHistoryRepository()));

        // Обновление адаптера
        adapter.updateData(profileViewModel.getTourHistory());
    }
}

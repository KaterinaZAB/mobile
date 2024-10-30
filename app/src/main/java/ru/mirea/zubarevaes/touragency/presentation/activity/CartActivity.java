package ru.mirea.zubarevaes.touragency.presentation.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.mirea.zubarevaes.touragency.R;
import ru.mirea.zubarevaes.touragency.data.repository.CartRepository;
import ru.mirea.zubarevaes.touragency.presentation.viewmodel.CartViewModel;
import ru.mirea.zubarevaes.touragency.domain.usecases.GetCartItems;
import ru.mirea.zubarevaes.touragency.presentation.adapters.TourAdapter;

public class CartActivity extends AppCompatActivity {
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private TourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView textView = new TextView(this);
        textView.setText("Здесь будет список добавленных туров в корзине");
        setContentView(textView);

        // Инициализация ViewModel
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // RecyclerView для отображения корзины
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Адаптер для туров в корзине
        adapter = new TourAdapter(cartViewModel.getCartItems());
        recyclerView.setAdapter(adapter);

        // Загрузка туров в корзине
        cartViewModel.loadCartItems(new GetCartItems(new CartRepository()));

        // Обновление данных в адаптере
        adapter.updateData(cartViewModel.getCartItems());
    }
}

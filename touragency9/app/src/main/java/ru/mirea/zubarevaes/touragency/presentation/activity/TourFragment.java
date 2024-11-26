package ru.mirea.zubarevaes.touragency.presentation.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.mirea.zubarevaes.domain.models.Tour;
import ru.mirea.zubarevaes.domain.repository.ITourRepository;
import ru.mirea.zubarevaes.domain.usecases.AddFavoriteTourUseCase;
import ru.mirea.zubarevaes.domain.usecases.DeleteFavoriteTourUseCase;
import ru.mirea.zubarevaes.touragency.R;
import ru.mirea.zubarevaes.touragency.presentation.activity.LoginActivity;


public class TourFragment extends Fragment {
//    private static final String ARG_TOUR = "TOUR";
//    private boolean isAuth = false;
//    private ITourRepository tourRepository;
//    private Tour tour;
//    private AddFavoriteTourUseCase addFavoriteTourUseCase;
//    private DeleteFavoriteTourUseCase deleteFavoriteTourUseCase;
//    private TextView textCountry;
//    private TextView textNameTour;
//    private TextView textDescription;
//    private TextView textPrice;
//    private Button buttonAddToFavorites;
//    private Button buttonDeleteFromFavorites;
//    private Button buttonGoBack;
//    private TextView textDate;
//    private ImageView imageTour;

//    public static TourFragment newInstance(
//            Tour tour, ITourRepository tourRepository, boolean isAuth) {
//        TourFragment fragment = new TourFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_TOUR, tour);
//        fragment.tourRepository = tourRepository;
//        fragment.isAuth = isAuth;
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            tour = (Tour) getArguments().getSerializable(ARG_TOUR);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tour, container, false);
//
//        addFavoriteTourUseCase = new AddFavoriteTourUseCase(tourRepository);
//        deleteFavoriteTourUseCase = new DeleteFavoriteTourUseCase(tourRepository);
//
//        textCountry = view.findViewById(R.id.country);
//        textNameTour = view.findViewById(R.id.nameTour);
//        textDescription = view.findViewById(R.id.decription);
//        textPrice = view.findViewById(R.id.price);
//        buttonAddToFavorites = view.findViewById(R.id.buttonAddToFavorite);
//        buttonDeleteFromFavorites = view.findViewById(R.id.buttonDeleteForFavorite);
//        buttonGoBack = view.findViewById(R.id.buttonBack);
//        textDate = view.findViewById(R.id.date);
//        imageTour = view.findViewById(R.id.imageView2);
//
//        if (tour != null) {
//            textCountry.setText("Япония"); // Update with the actual country
//            textNameTour.setText("Название поездки"); // Update with the actual tour name
//            textDescription.setText("текст описание"); // Update with the actual description
//            textPrice.setText("Цена: " + tour.getValue()); // Set the price
//            imageTour.setImageResource(R.drawable.tour_image); // Replace with the actual image resource
//
//            buttonAddToFavorites.setOnClickListener(v -> addToFavorites());
//            buttonDeleteFromFavorites.setOnClickListener(v -> deleteFromFavorites());
//            buttonGoBack.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
//
//            if (isAuth) {
//                checkIsFavorite(tour.getCharCode());
//            }
//        }
//
//        return view;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
//            String email = data.getStringExtra("EMAIL");
//            isAuth = true;
//
//            ((MainActivity) getActivity()).setAuthState(true, email);
//        }
//    }
//
//    public void updateAuthState(boolean isAuth) {
//        this.isAuth = isAuth;
//        buttonAddToFavorites.setVisibility(View.VISIBLE);
//        buttonDeleteFromFavorites.setVisibility(View.GONE);
//    }
//
//    private void checkIsFavorite(String charCode){
//        List<String> favoriteTours = tourRepository.getFavoriteTours();
//        updateUIButtonFavorite(favoriteTours.contains(charCode));
//    }
//
//    private void updateUIButtonFavorite(boolean isFavorite){
//        if (isFavorite) {
//            buttonAddToFavorites.setVisibility(View.INVISIBLE);
//            buttonDeleteFromFavorites.setVisibility(View.VISIBLE);
//        } else {
//            buttonAddToFavorites.setVisibility(View.VISIBLE);
//            buttonDeleteFromFavorites.setVisibility(View.GONE);
//        }
//    }
//
//    private void addToFavorites(){
//        if (isAuth) {
//            addFavoriteTourUseCase.execute(tour.getCharCode());
//            updateUIButtonFavorite(true);
//            Toast.makeText(requireContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(requireContext(), LoginActivity.class);
//            startActivityForResult(intent, 1);
//        }
//    }
//
//    private void deleteFromFavorites() {
//        if (isAuth) {
//            deleteFavoriteTourUseCase.execute(tour.getCharCode());
//            updateUIButtonFavorite(false);
//            Toast.makeText(requireContext(), "Удалено из избранного", Toast.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(requireContext(), LoginActivity.class);
//            startActivityForResult(intent, 1);
//        }
//    }
}

package ru.mirea.zubarevaes.mireaproject.ui.OSMMaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zubarevaes.mireaproject.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {
    private MapView mapView = null;
    private FragmentMapBinding binding;
    private static final int REQUEST_CODE_PERMISSION = 100;
    MyLocationNewOverlay locationNewOverlay;
    private List<Place> places; // список заведений

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMapBinding.inflate(inflater, container, false);

        // инициализация карты
        mapView = binding.mapView;
        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        // инициализация списка заведений
        initPlaces();

        // отображение заведений на карте
        showPlacesOnMap();

        return binding.getRoot();
    }

    private void initPlaces() {
        places = new ArrayList<>();
        places.add(new Place("МИРЭА - Российский техногологический университет", 55.794229, 37.700772, "РТУ МИРЭА — один из крупнейших российских университетов, осуществляющий подготовку по 112 направлениям и специальностям."));
        places.add(new Place("Кафе 'The Рыба'", 55.790448, 37.680517, "The Рыба — рыбный ресторан от создателей The Море, AsiaINN, Buonamici приглашает Вас окунуться в атмосферу любимых вкусов и эксклюзивных вин"));
        places.add(new Place("Кафе 'Эчпочмак'", 55.793232, 37.692411, "Главное блюдо в одноименном кафе - Эчпочмак – национальное татарское и башкирское блюдо, треугольные пирожки с мясной начинкой. "));
    }

    // отображение заведений на карте
    private void showPlacesOnMap() {
        for (Place place : places) {
            place(place);
        }
    }

    // отображение заведений на карте
    public void place(Place place) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(place.getLatitude(), place.getLongitude()));
        marker.setTitle(place.getName());
        marker.setSnippet(place.getDescription());
        mapView.getOverlays().add(marker);
    }

    // хранение информации о заведении
    private static class Place {
        private String name;
        private double latitude;
        private double longitude;
        private String description;

        public Place(String name, double latitude, double longitude, String description) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getDescription() {
            return description;
        }
    }
}


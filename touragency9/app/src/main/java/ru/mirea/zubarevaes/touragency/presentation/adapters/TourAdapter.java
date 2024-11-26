package ru.mirea.zubarevaes.touragency.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.zubarevaes.touragency.R;
import ru.mirea.zubarevaes.domain.models.Tour;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {

    public interface OnTourClickListener {
        void onTourClick(Tour tour);
    }

    private List<Tour> tourList;
    private OnTourClickListener onTourClickListener;

    public TourAdapter(List<Tour> tourList, OnTourClickListener onTourClickListener) {
        this.tourList = tourList;
        this.onTourClickListener = onTourClickListener;
    }

    public static class TourViewHolder extends RecyclerView.ViewHolder {
        TextView tourName;
        TextView tourPrice;
        ImageView tourImage;
        TextView tourDestination;
        TextView tourDepartureDate;

        public TourViewHolder(View itemView) {
            super(itemView);
            tourName = itemView.findViewById(R.id.tour_name);
            tourPrice = itemView.findViewById(R.id.tour_price);
            tourImage = itemView.findViewById(R.id.imageView);
            tourDestination = itemView.findViewById(R.id.textDestination);
            tourDepartureDate = itemView.findViewById(R.id.textDepartureDate);
        }

        public void bind(Tour tour, OnTourClickListener listener) {
            tourName.setText(tour.getName());
            tourPrice.setText(String.valueOf(tour.getPrice()));
            tourImage.setImageResource(R.mipmap.ic_launcher_foreground);
            tourDestination.setText(tour.getDestination());
            tourDepartureDate.setText(tour.getDepartureDate());

            itemView.setOnClickListener(v -> listener.onTourClick(tour));
        }
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour, parent, false); // Update to your layout resource
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);

        holder.bind(tour, onTourClickListener);

    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }
}

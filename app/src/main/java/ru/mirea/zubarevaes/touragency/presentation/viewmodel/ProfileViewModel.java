package ru.mirea.zubarevaes.touragency.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import java.util.List;
import ru.mirea.zubarevaes.touragency.domain.models.Tour;
import ru.mirea.zubarevaes.touragency.domain.usecases.GetTourHistory;

public class ProfileViewModel extends ViewModel {
    private List<Tour> tourHistory;

    public List<Tour> getTourHistory() {
        return tourHistory;
    }
    public void setTourHistory(List<Tour> tourHistory) {
        this.tourHistory = tourHistory;
    }

    public void loadTourHistory(int userId, GetTourHistory getTourHistoryUseCase) {
        tourHistory = getTourHistoryUseCase.execute(userId);
    }
}
package ru.mirea.zubarevaes.touragency.domain.usecases;

import ru.mirea.zubarevaes.touragency.data.repository.ValutesAPI;
import ru.mirea.zubarevaes.touragency.domain.repository.IGetInfoAboutValutes;

public class GetInfoAboutValutes implements IGetInfoAboutValutes {
    private ValutesAPI valutesAPI;

    public GetInfoAboutValutes(ValutesAPI valutesAPI) {
        this.valutesAPI = valutesAPI;
    }

    @Override
    public String execute() {
        return valutesAPI.getExchangeRate();
    }
}
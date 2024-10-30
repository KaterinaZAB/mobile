package ru.mirea.zubarevaes.touragency.data.repository;

import ru.mirea.zubarevaes.touragency.domain.repository.IValutesAPI;

public class ValutesAPI implements IValutesAPI {
    @Override
    public String getExchangeRate() {
        return "USD: 1.00, EUR: 0.85";
    }
}


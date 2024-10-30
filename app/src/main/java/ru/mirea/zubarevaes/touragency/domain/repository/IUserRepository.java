package ru.mirea.zubarevaes.touragency.domain.repository;

import ru.mirea.zubarevaes.touragency.domain.models.User;

public interface IUserRepository {
    User findUserByCredentials(String username, String password);
}
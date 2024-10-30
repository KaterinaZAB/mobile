package ru.mirea.zubarevaes.touragency.data.repository;

import ru.mirea.zubarevaes.touragency.domain.models.User;
import ru.mirea.zubarevaes.touragency.domain.repository.IUserRepository;

public class UserRepository implements IUserRepository {
    @Override
    public User findUserByCredentials(String username, String password) {
        return new User(1, "testuser", "testpassword");
    }
}

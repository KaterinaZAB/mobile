package ru.mirea.zubarevaes.touragency.domain.usecases;

import ru.mirea.zubarevaes.touragency.data.repository.UserRepository;
import ru.mirea.zubarevaes.touragency.domain.models.User;
import ru.mirea.zubarevaes.touragency.domain.repository.ICheckAuthorization;
import ru.mirea.zubarevaes.touragency.domain.repository.IUserRepository;

public class CheckAuthorization implements ICheckAuthorization {
    private IUserRepository userRepository;

    public CheckAuthorization(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean execute(String username, String password) {
        User user = userRepository.findUserByCredentials(username, password);
        return user != null;
    }
}

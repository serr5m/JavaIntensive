package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@RequiredArgsConstructor
public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    boolean authenticate(String login, String password) throws EntityNotFoundException, AlreadyAuthenticatedException {
        User user = usersRepository.findByLogin(login);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("User not found");
        }
        if (user.isAuthenticated()) {
            throw new AlreadyAuthenticatedException("this user already authenticated");
        }
        if (user.getPassword().equals(password)) {
            user.setAuthenticated(true);
            usersRepository.update(user);
            return true;
        }
        return false;
    }
}

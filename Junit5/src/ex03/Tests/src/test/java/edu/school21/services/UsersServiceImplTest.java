package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.any;

//import static org.mockito.ArgumentMatchers.any;

public class UsersServiceImplTest {
    private UsersRepository usersRepositoryMock;
    private UsersServiceImpl usersService;

    @BeforeEach
    public void setUp() {
        usersRepositoryMock = Mockito.mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepositoryMock);

    }

    @Test
    public void authenticateTestCorrectUserdata() throws AlreadyAuthenticatedException {
        String login = "aboba";
        String password = "12345";
        User user = new User(1, login, password, false);
        Mockito.when(usersRepositoryMock.findByLogin(login)).thenReturn(user);
        Mockito.doNothing().when(usersRepositoryMock).update(user);
        usersService.authenticate(login, password);
        Assertions.assertTrue(user.isAuthenticated());
    }

    @Test
    public void authenticateTestIncorrectLogin() throws AlreadyAuthenticatedException {
        String login = "aboba";
        String password = "12345";
        User user = new User(1, login, password, false);
        Mockito.when(usersRepositoryMock.findByLogin(login)).thenReturn(user);
        Mockito.doNothing().when(usersRepositoryMock).update(user);

        Assertions.assertThrows(EntityNotFoundException.class, () -> usersService.authenticate("petushok", password));
    }

    @Test
    public void authenticateTestIncorrectPassword() throws AlreadyAuthenticatedException {
        String login = "aboba";
        String password = "12345";
        User user = new User(1, login, password, false);
        Mockito.when(usersRepositoryMock.findByLogin(login)).thenReturn(user);
        Mockito.doNothing().when(usersRepositoryMock).update(user);

        Assertions.assertThrows(EntityNotFoundException.class, () -> usersService.authenticate(login, "12344"));
    }

}

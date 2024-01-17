package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepositoryMock;
    @InjectMocks
    private UsersServiceImpl usersService;

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
    public void authenticateTestIncorrectLogin() {
        String incorrectLogin = "bobababo";
        String password = "12345";
        Mockito.when(usersRepositoryMock.findByLogin(incorrectLogin)).thenReturn(null);
        Assertions.assertThrows(EntityNotFoundException.class, () -> usersService.authenticate(incorrectLogin, password));
        Mockito.verify(usersRepositoryMock).findByLogin(incorrectLogin);
    }

    @Test
    public void authenticateTestIncorrectPassword() throws AlreadyAuthenticatedException {
        String login = "aboba";
        String password = "123";
        String wrongPassword = "12345";
        User user = new User(1, login, password, false);
        Mockito.when(usersRepositoryMock.findByLogin(login)).thenReturn(user);

        Assertions.assertFalse(usersService.authenticate(login, wrongPassword));
        Mockito.verify(usersRepositoryMock, Mockito.times(1)).findByLogin(login);
        Mockito.verify(usersRepositoryMock, Mockito.never()).update(user);
    }

}

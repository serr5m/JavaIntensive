package edu.school21.service.repositories;

import edu.school21.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.
        return Optional.empty();
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}

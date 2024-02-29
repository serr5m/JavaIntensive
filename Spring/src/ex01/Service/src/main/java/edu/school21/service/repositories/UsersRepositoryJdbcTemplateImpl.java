package edu.school21.service.repositories;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    @Override
    public Optional findByEmail(String email) {
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

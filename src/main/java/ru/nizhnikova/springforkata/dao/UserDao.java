package ru.nizhnikova.springforkata.dao;

import ru.nizhnikova.springforkata.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    void delete(User user);

    User findById(Long id);

    List<User> listUsers();
}

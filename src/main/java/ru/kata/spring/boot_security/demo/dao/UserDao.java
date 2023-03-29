package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsersList();
    void addUser(User user);
    void deleteUser(Long id);
    User findUserByUsername(String email);
    User findUserById(Long id);
    void updateUser(User user);
}

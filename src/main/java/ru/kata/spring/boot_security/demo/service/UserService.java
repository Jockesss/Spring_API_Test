package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Repository
public interface UserService {
   List<User> getUsersList();
   void addUser(User user);
   void deleteUser(Long id);
   User findUserByUsername(String email);
   User findUserById(Long id);
   void updateUser(User user);
}

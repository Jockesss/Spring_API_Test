package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.Arrays;

@Component
public class UserUpload implements CommandLineRunner {

    private final UserServiceImp userServiceImp;
    @Autowired
    public UserUpload(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userServiceImp.addUser(new User("Andrew", "Kim", (byte)24, "qwe123", "admin@mail.ru", Role.ROLE_ADMIN, Role.ROLE_USER));
        userServiceImp.addUser(new User("Victor", "Ten", (byte)24, "qwe123", "user@mail.ru", Role.ROLE_USER));
//
    }
}

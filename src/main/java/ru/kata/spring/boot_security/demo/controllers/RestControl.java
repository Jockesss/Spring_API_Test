package ru.kata.spring.boot_security.demo.controllers;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class RestControl {

    private final UserServiceImp userServiceImp;

    @Autowired
    public RestControl(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }
    @GetMapping()
    public ModelAndView usersManage(Model model, Authentication authentication) {
        Role[] rolesArr = Role.values();
        model.addAttribute("roles_list", rolesArr);
        model.addAttribute("principal", authentication.getPrincipal());
        return new ModelAndView("admin_page");
    }

    @GetMapping("/getusers")
    public List<User> getUser() {
        return userServiceImp.getUsersList();
    }

    @PostMapping()
    public ResponseEntity addNewUser(@RequestBody User user) {
        user.setEnabled(true);
        userServiceImp.addUser(user);
        ResponseEntity<User> response = new ResponseEntity<>(userServiceImp.findUserByUsername(user.getEmail()), HttpStatus.CREATED);
        return response;
    }

    @PatchMapping ()
    public User editUser(@RequestBody User editUser) {
        if (editUser.getPassword().equals("")) {
            User user = userServiceImp.findUserById(editUser.getId());
            editUser.setPassword(user.getPassword());
        }
        userServiceImp.updateUser(editUser);
        return userServiceImp.findUserById(editUser.getId());
    }

    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody String id) throws ParseException {
        LinkedHashMap<String, Object> jsonObject = new JSONParser(id).parseObject();
        String strId = (String) jsonObject.get("id");
        try {
            userServiceImp.deleteUser(Long.parseLong(strId));
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/users")
//    public ResponseEntity<User> getUser(@AuthenticationPrincipal User user) {
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }



}

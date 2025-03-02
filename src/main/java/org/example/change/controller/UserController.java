package org.example.change.controller;

import org.example.change.entity.User;
import org.example.change.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/save")
    public void save(User user) {
        userService.save(user);
    }


}

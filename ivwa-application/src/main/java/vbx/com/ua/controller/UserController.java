package vbx.com.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vbx.com.ua.domain.User;
import vbx.com.ua.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return null;
    }

    @GetMapping
    public List<User> getAll() {
        return null;
    }

    public void create(@RequestBody User user) {

    }

}

package com.MyCityVoice.proj.controller;

import com.MyCityVoice.proj.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.MyCityVoice.proj.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getOne (@PathVariable Long id){
        return userService.getUser(id).orElseThrow();
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}

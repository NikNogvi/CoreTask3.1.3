package com.example.boot.controllers;

import com.example.boot.model.User;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {
    private final UserService userService;

    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<>(userService.usersList(), HttpStatus.OK);
    }

    @PostMapping(value = "/newuser")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//find user by id
    @GetMapping(value = "findUser/{id}")
    public ResponseEntity<User> showUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.showById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user, user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value ="/infoUser")
    @ResponseBody
    public User infoUser(@AuthenticationPrincipal User user){
        return userService.getUserByLogin(user.getEmail());
    }

}

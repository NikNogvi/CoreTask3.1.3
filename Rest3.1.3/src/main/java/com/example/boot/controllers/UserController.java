package com.example.boot.controllers;

import com.example.boot.model.User;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showUser1(@AuthenticationPrincipal User userModel, Model model) {
        User user = userService.showById(userModel.getId());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        User user = userService.showById(id);
        model.addAttribute("user", user);
        return "user";
    }


}

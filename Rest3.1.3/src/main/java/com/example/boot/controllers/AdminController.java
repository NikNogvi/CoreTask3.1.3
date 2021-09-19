package com.example.boot.controllers;

import com.example.boot.model.Role;
import com.example.boot.model.User;
import com.example.boot.service.RoleService;
import com.example.boot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.usersList());
        model.addAttribute("newUser", new User());
        List<Role> roles = roleService.rolesList();
        model.addAttribute("roles", roles);
        return "users";
    }
//
//    @GetMapping("/newuser")
//    public String newUser(Model model) {
//        model.addAttribute("newUser", new User());
//        return "newuser";
//    }
//
//
//    @PostMapping("/newuser")
//    public String createUser(@ModelAttribute("userModel") User user, @RequestParam(value = "roles") Long[] role) {
//        Set<Role> roleSet = new HashSet<>();
//        for (Long roles : role) {
//            roleSet.add(roleService.findRoleById(roles));
//        }
//        user.setRoles(roleSet);
//        userService.save(user);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping("/update")
//    public String edit(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userService.showById(id));
//        model.addAttribute("roles", roleService.rolesList());
//        return "update";
//    }
//
//    @PatchMapping("/update")
//    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") Long[] role) {
//        Set<Role> roleSet = new HashSet<>();
//        for (Long roles : role) {
//            roleSet.add(roleService.findRoleById(roles));
//        }
//        Long id = user.getId();
//        user.setRoles(roleSet);
//        userService.update(user, id);
//        return "redirect:/admin/";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam (value = "id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin/";
//    }
//
//
//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.showById(id);
//        model.addAttribute("user", user);
//        return "user";
//    }

}

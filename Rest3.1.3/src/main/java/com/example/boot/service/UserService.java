package com.example.boot.service;



import com.example.boot.model.Role;
import com.example.boot.model.User;

import java.util.List;

public interface UserService {
    List<User> usersList();
    User showById(long id);
    void save(User user);
    void delete(long id);
    void update(User user, long id);
    User getUserByLogin(String email);
    Role getRoleByName(String role);
}

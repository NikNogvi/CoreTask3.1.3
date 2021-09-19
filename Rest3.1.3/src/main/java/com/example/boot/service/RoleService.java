package com.example.boot.service;

import com.example.boot.model.Role;


import java.util.List;

public interface RoleService  {
    List<Role> rolesList();
    Role findRoleById(long id);
}

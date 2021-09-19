package com.example.boot.service;

import com.example.boot.DAO.RoleDAO;
import com.example.boot.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> rolesList() {
        return roleDAO.findAll();
    }

    @Override
    public Role findRoleById(long id) {
        Optional<Role> userRoles = roleDAO.findById(id);
        return userRoles.get();
    }

}

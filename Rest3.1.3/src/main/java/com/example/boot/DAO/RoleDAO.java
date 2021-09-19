package com.example.boot.DAO;

import com.example.boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}

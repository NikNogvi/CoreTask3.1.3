package com.example.boot.DAO;

import com.example.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query("select um FROM User um inner JOIN FETCH um.roles where um.email = :email")
    User findByUserEmail(@Param("email") String email);
}
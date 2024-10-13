package com.dailycodework.dreamshops.repository;


import com.dailycodework.dreamshops.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Users findByEmail(String email);
}

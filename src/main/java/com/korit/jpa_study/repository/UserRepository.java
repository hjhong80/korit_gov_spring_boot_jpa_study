package com.korit.jpa_study.repository;

import com.korit.jpa_study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
//    Optional<User> findByUserId(Integer userId);
}

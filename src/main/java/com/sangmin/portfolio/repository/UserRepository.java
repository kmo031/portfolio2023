package com.sangmin.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangmin.portfolio.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);
    boolean existsUsersByEmail(String email);
	Users findByUserName(String userName);
}
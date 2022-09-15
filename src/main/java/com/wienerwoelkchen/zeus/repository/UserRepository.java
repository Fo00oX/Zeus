package com.wienerwoelkchen.zeus.repository;

import java.util.Optional;

import com.wienerwoelkchen.zeus.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}

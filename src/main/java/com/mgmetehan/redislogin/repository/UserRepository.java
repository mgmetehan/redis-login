package com.mgmetehan.redislogin.repository;

import com.mgmetehan.redislogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}

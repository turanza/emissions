package com.triply.emissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.triply.emissions.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}

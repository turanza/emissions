package com.triply.emissions.service;

import com.triply.emissions.model.User;
import com.triply.emissions.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUserName(String username) {
        return userRepository.findByLogin(username);
    }
}

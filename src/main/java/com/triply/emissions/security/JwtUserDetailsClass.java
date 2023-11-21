package com.triply.emissions.security;

import com.triply.emissions.model.User;
import com.triply.emissions.security.jwt.JwtUser;
import com.triply.emissions.security.jwt.JwtUserFactory;
import com.triply.emissions.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsClass implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if(user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}

package com.triply.emissions.security.jwt;

import com.triply.emissions.model.User;
import io.jsonwebtoken.Jwt;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class JwtUserFactory {

    public static JwtUser create (User user){
        return new JwtUser(user.getId(),
                user.getName(),
                user.getSurName(),
                user.getLogin(),
                user.getPassword(),
                user.getStatus(),
                mapToGrantedAuthorities(user.getRole())
                );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
        List <GrantedAuthority>  list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }
}

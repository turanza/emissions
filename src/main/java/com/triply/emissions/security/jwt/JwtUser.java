package com.triply.emissions.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.triply.emissions.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    public JwtUser(Long id, String name, String surName, String login, String password, Status status, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.status = status;
        this.enabled = setEnabled();
        this.authorities = authorities;
    }
    private final Long id;

    private final String name;

    private final String surName;

    private final String login;

    private final String password;


    private final Status status;

    private final boolean enabled;

    private final Collection<? extends  GrantedAuthority> authorities;

    @JsonIgnore
    public Long getId(){return id;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean setEnabled() {
        if(status.equals(Status.ACTIVE))
            return true;
        else
            return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

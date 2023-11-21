package com.triply.emissions.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "login")
        })
@Getter
@Setter
public class User extends BaseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surName;

    @Column (name = "login")
    private String login;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;
}

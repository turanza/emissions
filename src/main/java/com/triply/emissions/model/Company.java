package com.triply.emissions.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter
@NoArgsConstructor
@Table(name = "company", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "id"),
        })
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Company(String name) {
        this.name = name;
    }
}

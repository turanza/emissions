package com.triply.emissions.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "id"),
        })
public class Vehicle extends BaseVehicleEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "licensePlate")
        private String licensePlate;

        @Column(name = "type")
        private String type;

        @Column(name = "baseEmission")
        private Double baseEmission;
}

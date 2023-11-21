package com.triply.emissions.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "mileage")
@Getter
public class Mileage {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "week")
    private Integer week;

    @Column(name = "mileage")
    private Double mileage;
}

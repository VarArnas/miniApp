package com.example.miniapp.entities.JPA;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToMany
    @JoinTable(
            name = "car_car_part",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "car_part_id")
    )
    private List<CarPart> parts = new ArrayList<>();
}

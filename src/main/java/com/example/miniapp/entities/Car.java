package com.example.miniapp.entities;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "car_car_part",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "car_part_id")
    )
    private List<CarPart> parts = new ArrayList<>();

    public void addPart(CarPart part) {
        parts.add(part);
        if (!part.getCars().contains(this)) {
            part.addCar(this);
        }
    }

    public void removePart(CarPart part) {
        parts.remove(part);
        if(part.getCars().contains(this)) {
            part.removeCar(this);
        }
    }
}

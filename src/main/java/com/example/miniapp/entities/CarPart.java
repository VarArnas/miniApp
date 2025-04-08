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
@Table(name = "car_part")
public class CarPart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "parts")
    private List<Car> cars = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_shop_id", nullable = false)
    private MechanicShop mechanicShop;

    public void addCar(Car car) {
        cars.add(car);
        if (!car.getParts().contains(this)) {
            car.addPart(this);
        }
    }

    public void removeCar(Car car) {
        cars.remove(car);
        if (car.getParts().contains(this)) {
            car.removePart(this);
        }
    }
}

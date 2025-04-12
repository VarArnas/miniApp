package com.example.miniapp.repositories;

import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByParts_Id(UUID carPartId);

    Car findByModel(String model);
}

package com.example.miniapp.controllers;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.mappers.CarMapper;
import com.example.miniapp.services.CarService;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping
    public List<UpdateCarDTO> getAllCars() {
        return carService.getAllCars().stream()
                .map(carMapper::toUpdateCarDTONoParts)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateCarDTO> getCarById(@PathVariable UUID id) {
        return ResponseEntity.ok(carMapper.toUpdateCarDTO(carService.getCarByIdWithParts(id)));
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody InsertCarDTO insertCarDTO) {
        carService.createCar(insertCarDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable UUID id, @RequestBody UpdateCarDTO updateCarDTO) {
        updateCarDTO.setId(id);
        carService.updateCar(updateCarDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }
}

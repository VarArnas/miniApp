package com.example.miniapp.services;


import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.CarMapper;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;
    private final CarPartRepository carPartRepository;
    private final CarMapper carMapper;

    //DML
    public void removeCars(List<Car> cars){
        carRepository.deleteAll(cars);
    }

    public Car createCar(InsertCarDTO carDTO) {

        Car car = carMapper.toCar(carDTO);
        carDTO.getParts().forEach(part -> {
            car.addPart(carPartRepository.getReferenceById(part));
        });

        return carRepository.save(car);
    }

    public Car updateCar(UpdateCarDTO carDTO) {
        Car car = carRepository.findById(carDTO.getId()).orElseThrow();
        carMapper.toCar(carDTO, car);

        List<CarPart> deleteCarParts = car.getParts().stream()
                        .filter(part -> carDTO.getParts().contains(part.getId()))
                        .toList();

        for (CarPart carPart : deleteCarParts) {
            car.removePart(carPart);
        }

        List<UUID> partsIds = car.getParts().stream()
                        .map(CarPart::getId)
                        .toList();

        carDTO.getParts().forEach(partId -> {
            if(!partsIds.contains(partId)){
                car.addPart(carPartRepository.getReferenceById(partId));
            }
        });

        return car;
    }

    //DQL
    @Transactional(readOnly = true)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Car> getCarsByPartId(UUID partId) {
        return carRepository.findByParts_Id(partId);
    }

    @Transactional(readOnly = true)
    public Car getCarByIdWithParts(UUID id) {
        Car car = carRepository.findById(id).orElseThrow();
        Hibernate.initialize(car.getParts());
        return car;
    }
}

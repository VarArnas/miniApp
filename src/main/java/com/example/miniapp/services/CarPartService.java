package com.example.miniapp.services;

import com.example.miniapp.dtos.InsertPartDTO;
import com.example.miniapp.dtos.UpdatePartDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.mappers.carPartMappers.CarPartMapper;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
@Transactional
public class CarPartService {
    private final CarPartRepository carPartRepository;
    private final CarPartMapper carPartMapper;
    private final CarRepository carRepository;


    //DML
    public void removeCarParts(List<CarPart> carParts){
        carPartRepository.deleteAll(carParts);
    }

    public CarPart createCarPart(InsertPartDTO partDTO) {
        CarPart carPart = carPartMapper.toCarPart(partDTO);

        List<Car> references = new ArrayList<>();
        partDTO.getCars().forEach(carId -> {
            references.add(carRepository.getReferenceById(carId));
        });

        carPart.getCars().addAll(references);
        return carPartRepository.save(carPart);
    }

    public CarPart updateCarPart(UpdatePartDTO partDTO) {
        CarPart carPart = carPartMapper.toCarPart(partDTO);

        List<Car> references = new ArrayList<>();
        partDTO.getCars().forEach(carId -> {
            references.add(carRepository.getReferenceById(carId));
        });

        carPart.getCars().addAll(references);
        return carPartRepository.save(carPart);
    }

    //DQL
    public List<CarPart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    public List<CarPart> getPartsByShopId(UUID shopId) {
        return carPartRepository.findAllByMechanicShop_Id(shopId);
    }



}

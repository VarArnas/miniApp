package com.example.miniapp.services;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.CarPartMapper;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        carPart.setCars(
                partDTO.getCars().stream()
                        .map(carRepository::getReferenceById)
                        .toList()
        );


        return carPartRepository.save(carPart);
    }

    public CarPart updateCarPart(UpdatePartDTO partDTO) {
        CarPart carPart = carPartRepository.findById(partDTO.getId()).orElseThrow();
        carPartMapper.toCarPart(partDTO, carPart);

        carPart.setCars(
                partDTO.getCars().stream()
                        .map(carRepository::getReferenceById)
                        .toList()
        );

        return carPart;
    }

    public List<CarPart> reassignCarParts(List<UUID> carParts, MechanicShop shop) {

        List<CarPart> partsToAdd = carPartRepository.findAllById(carParts);

        List<CarPart> deleteCarParts = shop.getParts().stream()
                .filter(part -> !carParts.contains(part.getId()))
                .toList();

        if(!deleteCarParts.isEmpty()){
            removeCarParts(deleteCarParts);
        }

        partsToAdd.forEach(part -> {
            if(!part.getMechanicShop().getId().equals(shop.getId())){
                part.setMechanicShop(shop);
            }
        });

        return partsToAdd;
    }

    public void deleteCarPart(UUID id) {
        carPartRepository.deleteById(id);
    }

    //DQL
    @Transactional(readOnly = true)
    public List<CarPart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CarPart> getPartsByShopId(UUID shopId) {
        return carPartRepository.findAllByMechanicShop_Id(shopId);
    }

    @Transactional(readOnly = true)
    public List<CarPart> getPartsByCarId(UUID carId) {
        return carPartRepository.findByCars_Id(carId);
    }

    @Transactional(readOnly = true)
    public CarPart getCarPartByIdWithCars(UUID carPartId) {
        CarPart part = carPartRepository.findById(carPartId).orElseThrow();
        Hibernate.initialize(part.getCars());
        return part;
    }
}

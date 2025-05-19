package com.example.miniapp.services;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.interfaces.MeasureExecution;
import com.example.miniapp.mappers.CarPartMapper;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    //DML
    public void removeCarParts(List<CarPart> carParts){
        carPartRepository.deleteAll(carParts);
    }

    @MeasureExecution
    public CarPart createCarPart(InsertPartDTO partDTO) {
        CarPart carPart = carPartMapper.toCarPart(partDTO);

        carPart.setCars(
                partDTO.getCars().stream()
                        .map(carRepository::getReferenceById)
                        .toList()
        );

        return carPartRepository.save(carPart);
    }

    @MeasureExecution
    public CarPart updateCarPart(UpdatePartDTO partDTO) {
        CarPart part;
        try{
            CarPart detachedCarPart = new CarPart();
            detachedCarPart.setId(partDTO.getId());
            detachedCarPart.setVersion(partDTO.getVersion());
            part = em.merge(detachedCarPart);
        } catch(OptimisticLockException e){
            System.out.println("exception caught!!!");
            throw e;
        }

        carPartMapper.toCarPart(partDTO, part);
        part.setCars(
                partDTO.getCars().stream()
                        .map(carRepository::getReferenceById)
                        .toList()
        );

        return part;
    }

    @MeasureExecution
    public List<CarPart> reassignCarPartsToMechanic(List<UUID> carParts, MechanicShop shop) {

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

    @MeasureExecution
    public void deleteCarPart(UUID id) {
        carPartRepository.deleteById(id);
    }

    //DQL
    @MeasureExecution
    @Transactional(readOnly = true)
    public List<CarPart> getAllCarParts() {
        return carPartRepository.findAll();
    }

    @MeasureExecution
    @Transactional(readOnly = true)
    public List<CarPart> getPartsByShopId(UUID shopId) {
        return carPartRepository.findAllByMechanicShop_Id(shopId);
    }

    @Transactional(readOnly = true)
    public List<CarPart> getPartsByCarId(UUID carId) {
        return carPartRepository.findByCars_Id(carId);
    }

    @MeasureExecution
    @Transactional(readOnly = true)
    public CarPart getCarPartByIdWithCars(UUID carPartId) {
        CarPart part = carPartRepository.findById(carPartId).orElseThrow();
        Hibernate.initialize(part.getCars());
        return part;
    }
}

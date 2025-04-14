package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.mappers.CarMapper;
import com.example.miniapp.services.CarService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Update;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Scope("session")
@Getter
@Setter
@RequiredArgsConstructor
public class CarBackingBean {

    private final CarService carService;
    private final CarMapper carMapper;

    public List<UpdateCarDTO> cachedCarDTOS;
    public UpdateCarDTO updateCarDTO;
    public InsertCarDTO insertCarDTO = new InsertCarDTO("", new ArrayList<>());


    @PostConstruct
    public void init(){
        refreshCars();
    }

    public List<UpdateCarDTO> getAllCars(){
        return cachedCarDTOS;
    }

    public void refreshCars(){
        List<Car> cars = carService.getAllCars();
        cachedCarDTOS = cars.stream()
                .map(carMapper::toUpdateCarDTONoParts)
                .toList();
    }

    public void deleteCar(UUID carId){
        carService.deleteCar(carId);
        refreshCars();
    }

    public String goToWorkWithCar(){
        return "workWithCar?faces-redirect=true";
    }

    public String goToWorkWithCar(UUID carId){
        Car car = carService.getCarByIdWithParts(carId);
        updateCarDTO = carMapper.toUpdateCarDTO(car);
        return "workWithCar?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

    public String createNewCar(){
        carService.createCar(insertCarDTO);
        refreshCars();
        insertCarDTO.setModel("");
        insertCarDTO.setParts(new ArrayList<>());
        return goToHomePage();
    }

    public String updateCar(){
        carService.updateCar(updateCarDTO);
        refreshCars();
        return goToHomePage();
    }

}

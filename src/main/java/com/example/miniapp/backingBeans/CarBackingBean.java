package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.ReturnCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.dtos.carPart.ReturnPartDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.mappers.CarMapper;
import com.example.miniapp.services.CarService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

    public List<ReturnCarDTO> cachedCarDTOS;
    public ReturnCarDTO selectedCar;
    public UpdateCarDTO updateCarDTO;
    public InsertCarDTO insertCarDTO;


    @PostConstruct
    public void init(){
        refreshCars();
    }

    public List<ReturnCarDTO> getAllCars(){
        return cachedCarDTOS;
    }

    public void refreshCars(){
        List<Car> cars = carService.getAllCars();
        cachedCarDTOS = cars.stream()
                .map(carMapper::toReturnCarDTO)
                .toList();
    }

    public void deleteCar(UUID carId){
        carService.deleteCar(carId);
        refreshCars();
    }

    public String goToWorkWithCar(){
        return "workWithCar?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

//    public ReturnShopDTO updateShopById(){
//        MechanicShop updatedShop = shopService.updateShop(updateShopDTO);
//        return shopMapper.toReturnShopDTO(updatedShop);
//    }
//
//    public ReturnShopDTO createNewShop(){
//        MechanicShop createdShop = shopService.insertShop(insertShopDTO);
//        return shopMapper.toReturnShopDTO(createdShop);
//    }
}

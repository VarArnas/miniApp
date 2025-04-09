package com.example.miniapp.dataInitializer;

import com.example.miniapp.daos.ShopDAO;
import com.example.miniapp.dtos.InsertPartDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import com.example.miniapp.services.CarPartService;
import com.example.miniapp.services.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    DataTransactions dataTransactions;
    ShopDAO shopDAO;
    CarRepository carRepository;

    CarPartService carPartService;
    ShopService shopService;


    @Override
    public void run(String... args) {
        dataTransactions.first();
//        dataTransactions.five();
//        dataTransactions.second();
//        dataTransactions.three();
//        dataTransactions.four();

        MechanicShop shop = MechanicShop.builder()
                .id(UUID.randomUUID())
                .name("Lallala mechanics")
                .parts(new ArrayList<CarPart>())
                .build();

        shopDAO.insertShop(shop);
        List<Car> cars = carRepository.findAll();
        System.out.println("didnt carash is lazy");
        List<UUID> carReferences = cars.stream()
                .map(Car::getId)
                .toList();

        InsertPartDTO partDTO = new InsertPartDTO("newPart", carReferences, shop.getId());
        carPartService.createCarPart(partDTO);

        shopService.deleteShop(shop.getId());


    }




}

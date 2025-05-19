package com.example.miniapp.dataInitializer;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarRepository;
import com.example.miniapp.services.CarPartService;
import com.example.miniapp.services.CarService;
import com.example.miniapp.services.ShopService;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    DataTransactions dataTransactions;
    CarRepository carRepository;

    CarPartService carPartService;
    ShopService shopService;
    CarService carService;


    @Override
    public void run(String... args) throws InterruptedException {
        dataTransactions.first();
//        dataTransactions.five();
//        dataTransactions.second();
//        dataTransactions.three();
//        dataTransactions.four();

        MechanicShop shop =  shopService.insertShop(new InsertShopDTO("Lallala mechanics", new ArrayList<>()));

        List<Car> cars = carRepository.findAll();

        List<UUID> carReferences = cars.stream()
                .map(Car::getId)
                .toList();

        InsertPartDTO partDTO = new InsertPartDTO("newPart", carReferences, shop.getId());

        CarPart part = carPartService.createCarPart(partDTO);

        List<MechanicShop> shops = shopService.getAllShops();
        System.out.println(shops.get(0).getName());

        part = carPartService.getCarPartByIdWithCars(part.getId());
        part.getCars().remove(0);
        System.out.println("tired");


        carReferences = part.getCars().stream()
                .map(Car::getId)
                .toList();


        UpdatePartDTO updatePartDTO = new UpdatePartDTO(part.getId(), "changedName", carReferences, shops.get(0).getId(), part.getVersion());
        carPartService.updateCarPart(updatePartDTO);
        List<CarPart> parts = carPartService.getAllCarParts();
        List<UUID> changeParts = parts.stream()
                .map(CarPart::getId)
                .toList();

        shopService.insertShop(new InsertShopDTO("New insert", changeParts));

        MechanicShop updatedShop = shopService.updateShop(new UpdateShopDTO(shops.get(0).getId(), "changingNameOfShop",
                List.of(changeParts.get(0), changeParts.get(1))));

        shopService.updateShop(new UpdateShopDTO(updatedShop.getId(), "changingNameOfShop",
                List.of(changeParts.get(0), changeParts.get(2))));

        parts = carPartService.getAllCarParts();
        Car car = carRepository.findByModel("BMW M3");
        Car magic = carService.updateCar(new UpdateCarDTO(car.getId(), car.getModel(),
                parts.stream().map(CarPart::getId).toList()));

        carService.createCar(new InsertCarDTO("some new",
                List.of(parts.get(0).getId(), parts.get(2).getId())));
    }
}

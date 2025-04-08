package com.example.miniapp.dataInitializer;

import com.example.miniapp.daos.ShopMapper;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {
    private final CarRepository carRepository;
    private final CarPartRepository carPartRepository;
    private final ShopMapper shopMapper;

    @Override
    @Transactional
    public void run(String... args) {


        MechanicShop shop1 = MechanicShop.builder()
                .id(UUID.randomUUID())
                .name("Best mehcanics")
                .parts(new ArrayList<CarPart>())
                .build();
        MechanicShop shop2 = MechanicShop.builder()
                .id(UUID.randomUUID())
                .name("Worst mechanics")
                .parts(new ArrayList<CarPart>())
                .build();
        MechanicShop shop3 = MechanicShop.builder()
                .id(UUID.randomUUID())
                .name("Mid mechanics")
                .parts(new ArrayList<CarPart>())
                .build();

        shopMapper.insertShop(shop1);
        shopMapper.insertShop(shop2);
        shopMapper.insertShop(shop3);

        List<MechanicShop> shops = shopMapper.findAllShops();

        for(MechanicShop shop : shops) {
            System.out.println(shop.getId());
        }

        CarPart part1 = CarPart.builder()
                .name("Turbocharger")
                .cars(new ArrayList<Car>())
                .mechanicShop(shops.get(0))
                .build();

        CarPart part2 = CarPart.builder().name("Brake Pad")
                .cars(new ArrayList<Car>())
                .mechanicShop(shops.get(0))
                .build();

        CarPart part3 = CarPart.builder()
                .name("Suspension Kit")
                .cars(new ArrayList<Car>())
                .mechanicShop(shops.get(1))
                .build();

        carPartRepository.saveAll(List.of(part1, part2, part3));

        System.out.println("\n\nDone initializing MechanicShop and parts\n\n");

        Car car1 = Car.builder().model("Toyota Supra").parts(new ArrayList<CarPart>()).build();
        Car car2 = Car.builder().model("BMW M3").parts(new ArrayList<CarPart>()).build();
        Car car3 = Car.builder().model("Nissan Skyline").parts(new ArrayList<CarPart>()).build();

        carRepository.saveAll(List.of(car1, car2, car3));

        List<CarPart> carParts = carPartRepository.findAll();

        //adding parts/cars
        car1.addPart(carParts.get(0));
        car1.addPart(carParts.get(1));

        car2.addPart(carParts.get(1));
        car2.addPart(carParts.get(2));

        car3.addPart(carParts.get(0));
        car3.addPart(carParts.get(2));

        carRepository.saveAll(List.of(car1, car2, car3));

//
//        CarPart deletingPart = carParts.get(0);
//        for(Car car : deletingPart.getCars()){
//            car.removePart(deletingPart);
//        }
//
//        carRepository.saveAll(deletingPart.getCars());
//
//        carPartRepository.deleteById(carParts.get(0).getId());
//
//        System.out.println("Sample cars and parts added!");
    }

}

package com.example.miniapp.dataInitializer;

import com.example.miniapp.daos.ShopMapper;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataTransactions {
    private final CarRepository carRepository;
    private final CarPartRepository carPartRepository;
    private final ShopMapper shopMapper;

    @Transactional
    public void three(){
        List<CarPart> carParts = carPartRepository.findAll();
        System.out.println("\n Retrieved name: " + carParts.get(0).getName() + "\n");
        CarPart part1 = carParts.get(0);
        new ArrayList<>(part1.getCars()).forEach(car ->
                car.removePart(part1));

        carPartRepository.deleteById(carParts.get(0).getId());
    }

    @Transactional
    public void four(){
        List<CarPart> carParts = carPartRepository.findAll();
        System.out.println("\n Retrieved name: " + carParts.get(0).getName() + "\n");

        CarPart part1 = carParts.get(0);

        Car car = new Car().builder()
                .model("Ford")
                .parts(new ArrayList<>())
                .build();

        part1.addCar(car);
    }

    @Transactional
    public void second(){
        List<CarPart> carParts = carPartRepository.findAll();

        CarPart carPart1 = carParts.get(0);
        System.out.println("\n Retrieved name: " + carPart1.getName() + "\n");
        System.out.println(carPart1.getCars().get(0).getModel());
    }

    @Transactional
    public void first(){
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

        Car car1 = Car.builder().
                model("Toyota Supra").
                parts(new ArrayList<CarPart>(List.of(part1, part2)))
                .build();
        Car car2 = Car.builder()
                .model("BMW M3")
                .parts(new ArrayList<CarPart>(List.of(part2, part3)))
                .build();
        Car car3 = Car.builder()
                .model("Nissan Skyline")
                .parts(new ArrayList<CarPart>(List.of(part1, part3)))
                .build();

        carRepository.saveAll(List.of(car1, car2, car3));
    }

    @Transactional
    public void five(){
        List<MechanicShop> shops = shopMapper.findAllShops();
        MechanicShop shopmechan = shopMapper.findShopByIdWithParts(shops.get(0).getId());
        System.out.println(shopmechan.getId());
        System.out.println(shopmechan.getName());
        List<CarPart> shopParts = shopmechan.getParts();
        System.out.println(shopParts.isEmpty());
        for(CarPart part : shopParts) {
            System.out.println(part.getName());
            System.out.println(part.getMechanicShop().getId());
        }
    }
}

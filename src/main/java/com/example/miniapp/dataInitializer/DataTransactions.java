package com.example.miniapp.dataInitializer;

import com.example.miniapp.daos.ShopDAO;
import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import com.example.miniapp.services.CarPartService;
import com.example.miniapp.services.ShopService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class DataTransactions {
    private final CarRepository carRepository;
    private final CarPartRepository carPartRepository;
    private final ShopDAO shopDAO;

    private final ShopService shopService;
    private final CarPartService carPartService;


    @PersistenceContext
    private EntityManager em;

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

        Car car = Car.builder()
                .model("Ford")
                .parts(new ArrayList<>())
                .build();

        part1.getCars().add(car);
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

        //create 3 shops
        InsertShopDTO fakeShop1 = new InsertShopDTO("Best mechanics", new ArrayList<>());
        InsertShopDTO fakeShop2 = new InsertShopDTO("Worst mechanics", new ArrayList<>());
        InsertShopDTO fakeShop3 = new InsertShopDTO("Mid mechanics", new ArrayList<>());

        MechanicShop shop1 = shopService.insertShop(fakeShop1);
        MechanicShop shop2 = shopService.insertShop(fakeShop2);
        MechanicShop shop3 = shopService.insertShop(fakeShop3);

        //create 3 parts
        CarPart part1 = CarPart.builder()
                .name("Turbocharger")
                .cars(new ArrayList<Car>())
                .mechanicShop(shop1)
                .build();

        CarPart part2 = CarPart.builder().name("Brake Pad")
                .cars(new ArrayList<Car>())
                .mechanicShop(shop1)
                .build();

        CarPart part3 = CarPart.builder()
                .name("Suspension Kit")
                .cars(new ArrayList<Car>())
                .mechanicShop(shop2)
                .build();

//        System.out.println("shows all");
//        em.flush();
//        shopDAO.deleteShopById(shop1.getId());

        //create 3 cars
        Car car1 = Car.builder().
                model("Toyota Supra").
                parts(new ArrayList<CarPart>())
                .build();
        Car car2 = Car.builder()
                .model("BMW M3")
                .parts(new ArrayList<CarPart>())
                .build();
        Car car3 = Car.builder()
                .model("Nissan Skyline")
                .parts(new ArrayList<CarPart>())
                .build();


        //add parts to cars
        car1.addPart(part1);
        car1.addPart(part2);

        car2.addPart(part3);
        car2.addPart(part2);

        car3.addPart(part1);
        car3.addPart(part3);

        carRepository.saveAll(List.of(car1, car2, car3));

    }

    @Transactional
    public void five(){
        List<MechanicShop> shops = shopDAO.findAllShops();
        MechanicShop shopmechan = shopDAO.findShopByIdWithParts(shops.get(0).getId());
        System.out.println(shopmechan.getId());
        System.out.println(shopmechan.getName());
        List<CarPart> shopParts = shopmechan.getParts();
        System.out.println(shopParts.isEmpty());
        for(CarPart part : shopParts) {
            System.out.println(part.getName());
            System.out.println(part.getMechanicShop().getId());
        }


        shopDAO.deleteShopById(shops.get(0).getId());
    }
}

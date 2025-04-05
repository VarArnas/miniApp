package com.example.miniapp.dataInitializer;

import com.example.miniapp.entities.JPA.Car;
import com.example.miniapp.entities.JPA.CarPart;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {
    private final CarRepository carRepository;
    private final CarPartRepository carPartRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Create CarParts
        CarPart part1 = CarPart.builder().name("Turbocharger").cars(new ArrayList<Car>()).build();
        CarPart part2 = CarPart.builder().name("Brake Pad").cars(new ArrayList<Car>()).build();
        CarPart part3 = CarPart.builder().name("Suspension Kit").cars(new ArrayList<Car>()).build();

        carPartRepository.saveAll(List.of(part1, part2, part3));

        // Create Cars and assign parts
        Car car1 = Car.builder().model("Toyota Supra").parts(new ArrayList<CarPart>()).build();
        Car car2 = Car.builder().model("BMW M3").parts(new ArrayList<CarPart>()).build();
        Car car3 = Car.builder().model("Nissan Skyline").parts(new ArrayList<CarPart>()).build();

        carRepository.saveAll(List.of(car1, car2, car3));

        car1.getParts().addAll(List.of(part1, part2));
        car2.getParts().addAll(List.of(part2, part3));
        car3.getParts().addAll(List.of(part1, part3));


        System.out.println("Sample cars and parts added!");
    }

}

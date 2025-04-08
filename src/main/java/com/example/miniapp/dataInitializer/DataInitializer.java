package com.example.miniapp.dataInitializer;

import com.example.miniapp.daos.ShopMapper;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import com.example.miniapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    DataTransactions dataTransactions;

    @Override
    public void run(String... args) {
        dataTransactions.first();
        dataTransactions.five();
//        dataTransactions.second();
//        dataTransactions.three();
//        dataTransactions.four();
    }




}

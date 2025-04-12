package com.example.miniapp.mappers;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CarMapper {

    public Car toCar(InsertCarDTO carDTO) {
        return Car.builder()
                .model(carDTO.getModel())
                .parts(new ArrayList<>())
                .build();
    }

    public void toCar(UpdateCarDTO carDTO, Car car) {
        car.setModel(carDTO.getModel());
    }
}

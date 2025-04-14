package com.example.miniapp.mappers;

import com.example.miniapp.dtos.car.InsertCarDTO;
import com.example.miniapp.dtos.car.UpdateCarDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public UpdateCarDTO toUpdateCarDTO(Car car) {
        List<UUID> uuids = car.getParts().stream()
                .map(CarPart::getId)
                .toList();
        return new UpdateCarDTO(car.getId(), car.getModel(), uuids);
    }

    public UpdateCarDTO toUpdateCarDTONoParts(Car car) {
        return new UpdateCarDTO(car.getId(), car.getModel(), new ArrayList<>());
    }
}

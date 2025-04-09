package com.example.miniapp.mappers.carPartMappers;

import com.example.miniapp.dtos.InsertPartDTO;
import com.example.miniapp.dtos.UpdatePartDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CarPartMapper {

    //doesnt map arrays


    public CarPart toCarPart(InsertPartDTO partDTO) {
        return CarPart.builder()
                .name(partDTO.getName())
                .cars(new ArrayList<>())
                .mechanicShop(MechanicShop.builder().id(partDTO.getMechanicShop()).build())
                .build();
    }

    public CarPart toCarPart(UpdatePartDTO partDTO) {
        return CarPart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .cars(new ArrayList<>())
                .mechanicShop(MechanicShop.builder().id(partDTO.getMechanicShop()).build())
                .build();
    }

}

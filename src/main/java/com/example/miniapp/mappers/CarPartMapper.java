package com.example.miniapp.mappers;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CarPartMapper {

    public CarPart toCarPart(InsertPartDTO partDTO) {
        return CarPart.builder()
                .name(partDTO.getName())
                .cars(new ArrayList<>())
                .mechanicShop(MechanicShop.builder().id(partDTO.getMechanicShop()).build())
                .build();
    }

    public void toCarPart(UpdatePartDTO partDTO, CarPart carPart) {
       carPart.setName(partDTO.getName());
       carPart.setMechanicShop(MechanicShop.builder().id(partDTO.getMechanicShop()).build());
    }
}

package com.example.miniapp.mappers;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.ReturnPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.dtos.shop.ReturnShopDTO;
import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public ReturnPartDTO toReturnPartDTO(CarPart carPart) {
        return new ReturnPartDTO(carPart.getId(), carPart.getName(), new ArrayList<>(), Optional.empty());
    }
}

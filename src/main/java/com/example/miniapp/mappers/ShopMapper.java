package com.example.miniapp.mappers;

import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ShopMapper {

    public MechanicShop toMechanicShop(InsertShopDTO shopDTO) {
        return MechanicShop.builder()
                .id(UUID.randomUUID())
                .name(shopDTO.getName())
                .parts(new ArrayList<>())
                .build();
    }

    public void toMechanicShop(UpdateShopDTO shopDTO, MechanicShop shop) {
        shop.setName(shopDTO.getName());

    }
}

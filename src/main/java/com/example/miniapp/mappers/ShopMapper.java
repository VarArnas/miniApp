package com.example.miniapp.mappers;

import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public UpdateShopDTO toUpdateShopDTO(MechanicShop shop) {
        List<UUID> parts = shop.getParts().stream()
                .map(CarPart::getId)
                .toList();
        return new UpdateShopDTO(shop.getId(), shop.getName(), parts);
    }

    public UpdateShopDTO toUpdateShopDTONoParts(MechanicShop shop) {
        return new UpdateShopDTO(shop.getId(), shop.getName(), new ArrayList<>());
    }
}

package com.example.miniapp.services;

import com.example.miniapp.daos.ShopDAO;
import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.ShopMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
@Scope("application")
@Transactional
public class ShopService {
    private final ShopDAO shopDAO;
    private final CarPartService carPartService;
    private final ShopMapper shopMapper;

    //DML
    public MechanicShop insertShop(InsertShopDTO shopDTO) {
        MechanicShop shop = shopMapper.toMechanicShop(shopDTO);
        shopDAO.insertShop(shop);

        if(shopDTO.getParts() != null) {
            carPartService.reassignCarPartsToMechanic(shopDTO.getParts(), shop);
        }

        return shopDAO.findShopByIdWithParts(shop.getId());
    }

    public MechanicShop updateShop(UpdateShopDTO shopDTO) {
        MechanicShop shop = shopDAO.findShopByIdWithParts(shopDTO.getId());
        shopMapper.toMechanicShop(shopDTO, shop);
        carPartService.reassignCarPartsToMechanic(shopDTO.getParts(), shop);
        shopDAO.updateShop(shop);
        return shopDAO.findShopByIdWithParts(shop.getId());
    }

    public void deleteShop(UUID id) {
        shopDAO.deleteShopById(id);
    }

    //DQL
    @Transactional(readOnly = true)
    public MechanicShop getShopWithParts(UUID id) {
        return shopDAO.findShopByIdWithParts(id);
    }

    @Transactional(readOnly = true)
    public List<MechanicShop> getAllShops() {
        return shopDAO.findAllShops();
    }

    @Transactional(readOnly = true)
    public List<CarPart> getPartsOfShop(UUID id) {
        return carPartService.getPartsByShopId(id);
    }
}

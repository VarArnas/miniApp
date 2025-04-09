package com.example.miniapp.services;

import com.example.miniapp.daos.ShopDAO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.repositories.CarPartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
@Transactional
public class ShopService {
    private final ShopDAO shopDAO;
    private final CarPartService carPartService;
    private final CarPartRepository carPartRepository;

    @PersistenceContext
    private EntityManager em;

    //DML
    public MechanicShop insertShop(MechanicShop shop) {
        shop.setId(UUID.randomUUID());
        shopDAO.insertShop(shop);
        return shopDAO.getShopById(shop.getId());
    }

    public MechanicShop updateShop(MechanicShop shop) {
        shopDAO.updateShop(shop);
        return shopDAO.getShopById(shop.getId());
    }

    public void deleteShop(UUID id) {
        List<CarPart> carParts = carPartService.getPartsByShopId(id);
        carPartService.removeCarParts(carParts);
        em.flush();
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

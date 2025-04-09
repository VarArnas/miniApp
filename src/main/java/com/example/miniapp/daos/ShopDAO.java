package com.example.miniapp.daos;

import com.example.miniapp.entities.MechanicShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ShopDAO {
    void insertShop(MechanicShop shop);
    MechanicShop findShopByIdWithParts(@Param("id") UUID id);
    List<MechanicShop> findAllShops();
    void deleteShopById(@Param("id") UUID id);
    void updateShop(MechanicShop shop);
    MechanicShop getShopById(@Param("id") UUID id);
}

package com.example.miniapp.daos;

import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ShopMapper {
    void insertShop(MechanicShop shop);
    MechanicShop findShopByNameWithParts(@Param("id") UUID id);
    List<MechanicShop> findAllShops();
    void deleteShopById(@Param("id") UUID id);
}

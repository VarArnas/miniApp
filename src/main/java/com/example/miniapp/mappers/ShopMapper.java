package com.example.miniapp.mappers;

import com.example.miniapp.entities.MyBaitis.MechanicShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    MechanicShop selectShopById(Long id);
}

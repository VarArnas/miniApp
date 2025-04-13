package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.ReturnPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.ReturnShopDTO;
import com.example.miniapp.entities.CarPart;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.CarPartMapper;
import com.example.miniapp.services.CarPartService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Scope("session")
@Getter
@Setter
@RequiredArgsConstructor
public class CarPartBackingBean {

    private final CarPartService carPartService;
    private final CarPartMapper carPartMapper;

    public List<ReturnPartDTO> cachedPartDTOS;
    public ReturnPartDTO selectedPart;
    public UpdatePartDTO updatePartDTO;
    public InsertPartDTO insertPartDTO;


    @PostConstruct
    public void init(){
        refreshParts();
    }

    public List<ReturnPartDTO> getAllParts(){
        return cachedPartDTOS;
    }

    public void refreshParts(){
        List<CarPart> parts = carPartService.getAllCarParts();
        cachedPartDTOS = parts.stream()
                .map(carPartMapper::toReturnPartDTO)
                .toList();
    }

    public void deletePart(UUID partId){
        carPartService.deleteCarPart(partId);
        refreshParts();
    }

    public String goToWorkWithPart(){
        return "workWithPart?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

//    public ReturnShopDTO updateShopById(){
//        MechanicShop updatedShop = shopService.updateShop(updateShopDTO);
//        return shopMapper.toReturnShopDTO(updatedShop);
//    }
//
//    public ReturnShopDTO createNewShop(){
//        MechanicShop createdShop = shopService.insertShop(insertShopDTO);
//        return shopMapper.toReturnShopDTO(createdShop);
//    }
}

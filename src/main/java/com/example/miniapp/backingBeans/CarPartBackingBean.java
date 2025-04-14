package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.entities.CarPart;
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

@Component
@Scope("session")
@Getter
@Setter
@RequiredArgsConstructor
public class CarPartBackingBean {

    private final CarPartService carPartService;
    private final CarPartMapper carPartMapper;

    public List<UpdatePartDTO> cachedPartDTOS;
    public UpdatePartDTO updatePartDTO;
    public InsertPartDTO insertPartDTO = new InsertPartDTO("", new ArrayList<>(), null);


    @PostConstruct
    public void init(){
        refreshParts();
    }

    public List<UpdatePartDTO> getAllParts(){
        return cachedPartDTOS;
    }

    public void refreshParts(){
        List<CarPart> parts = carPartService.getAllCarParts();
        cachedPartDTOS = parts.stream()
                .map(carPartMapper::toReturnPartDTONoCars)
                .toList();
    }

    public void deletePart(UUID partId){
        carPartService.deleteCarPart(partId);
        refreshParts();
    }

    public String goToWorkWithPart(){
        return "workWithPart?faces-redirect=true";
    }

    public String goToWorkWithPart(UUID partID){
        CarPart part = carPartService.getCarPartByIdWithCars(partID);
        updatePartDTO = carPartMapper.toReturnPartDTO(part);
        return "workWithPart?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

    public String updatePart(){
        carPartService.updateCarPart(updatePartDTO);
        refreshParts();
        return "index?faces-redirect=true";
    }

    public String insertPart(){
        carPartService.createCarPart(insertPartDTO);
        refreshParts();

        insertPartDTO.setCars(new ArrayList<>());
        insertPartDTO.setName("");
        insertPartDTO.setMechanicShop(null);
        return "index?faces-redirect=true";
    }


}

package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.ShopMapper;
import com.example.miniapp.services.CarPartService;
import com.example.miniapp.services.ShopService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
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
public class ShopBackingBean {

    private final ShopService shopService;
    private final ShopMapper shopMapper;
    private final CarPartService carPartService;

    public List<UpdateShopDTO> cachedShopDTOS;
    public UpdateShopDTO updateShopDTO;
    public InsertShopDTO insertShopDTO = new InsertShopDTO("", new ArrayList<>());


    @PostConstruct
    public void init(){
        refreshShops();
    }

    public List<UpdateShopDTO> getAllMechanicShops(){
        return cachedShopDTOS;
    }

    public void refreshShops(){
        List<MechanicShop> parts = shopService.getAllShops();
        cachedShopDTOS = parts.stream()
                .map(shopMapper::toUpdateShopDTONoParts)
                .toList();
    }

    public void deleteMechanicShop(UUID shopId){
        shopService.deleteShop(shopId);
        refreshShops();
        refreshCarParts();
    }

    public String goToWorkWithMechanic(UUID shopId){
        MechanicShop shop = shopService.getShopWithParts(shopId);
        updateShopDTO = shopMapper.toUpdateShopDTO(shop);
        System.out.println(updateShopDTO.getName() + " and its part size: " + updateShopDTO.getParts().size());
        return "workWithMechanic?faces-redirect=true";
    }

    public String goToWorkWithMechanic(){
        return "workWithMechanic?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

    public String updateShop(){
        shopService.updateShop(updateShopDTO);
        refreshShops();
        refreshCarParts();
        return "index?faces-redirect=true";
    }

    public String insertShop(){
        shopService.insertShop(insertShopDTO);
        refreshShops();

        insertShopDTO.setName("");
        insertShopDTO.setParts(new ArrayList<>());
        return "index?faces-redirect=true";
    }

    public void refreshCarParts(){
        FacesContext context = FacesContext.getCurrentInstance();
        CarPartBackingBean carPartBean = context.getApplication()
                .evaluateExpressionGet(context, "#{carPartBackingBean}", CarPartBackingBean.class);
        carPartBean.refreshParts();
    }
}

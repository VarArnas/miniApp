package com.example.miniapp.backingBeans;

import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.ReturnShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.entities.MechanicShop;
import com.example.miniapp.mappers.ShopMapper;
import com.example.miniapp.services.ShopService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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

    public List<ReturnShopDTO> cachedShopDTOS;
    public ReturnShopDTO selectedShop;
    public UpdateShopDTO updateShopDTO;
    public InsertShopDTO insertShopDTO;


    @PostConstruct
    public void init(){
        refreshShops();
    }

    public List<ReturnShopDTO> getAllMechanicShops(){
        return cachedShopDTOS;
    }

    public void refreshShops(){
        List<MechanicShop> parts = shopService.getAllShops();
        cachedShopDTOS = parts.stream()
                .map(shopMapper::toReturnShopDTO)
                .toList();
    }

    public void deleteMechanicShop(UUID shopId){
        shopService.deleteShop(shopId);
        refreshShops();

        FacesContext context = FacesContext.getCurrentInstance();
        CarPartBackingBean carPartBean = context.getApplication()
                .evaluateExpressionGet(context, "#{carPartBackingBean}", CarPartBackingBean.class);
        carPartBean.refreshParts();
    }

    public String goToWorkWithMechanic(){
        return "workWithMechanic?faces-redirect=true";
    }

    public String goToHomePage(){
        return "index?faces-redirect=true";
    }

    public ReturnShopDTO updateShopById(){
        MechanicShop updatedShop = shopService.updateShop(updateShopDTO);
        return shopMapper.toReturnShopDTO(updatedShop);
    }

    public ReturnShopDTO createNewShop(){
        MechanicShop createdShop = shopService.insertShop(insertShopDTO);
        return shopMapper.toReturnShopDTO(createdShop);
    }
}

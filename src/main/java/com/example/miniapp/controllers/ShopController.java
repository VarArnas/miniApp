package com.example.miniapp.controllers;

import com.example.miniapp.dtos.shop.InsertShopDTO;
import com.example.miniapp.dtos.shop.UpdateShopDTO;
import com.example.miniapp.mappers.ShopMapper;
import com.example.miniapp.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @GetMapping
    public List<UpdateShopDTO> getAllShops() {
        return shopService.getAllShops().stream()
                .map(shopMapper::toUpdateShopDTONoParts)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateShopDTO> getShopById(@PathVariable UUID id) {
        return ResponseEntity.ok(shopMapper.toUpdateShopDTO(shopService.getShopWithParts(id)));
    }

    @PostMapping
    public ResponseEntity<Void> createShop(@RequestBody InsertShopDTO insertShopDTO) {
        shopService.insertShop(insertShopDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShop(@PathVariable UUID id, @RequestBody UpdateShopDTO updateShopDTO) {
        updateShopDTO.setId(id);
        shopService.updateShop(updateShopDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteShop(@PathVariable UUID id) {
        shopService.deleteShop(id);
    }
}

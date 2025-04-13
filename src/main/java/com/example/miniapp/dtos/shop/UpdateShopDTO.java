package com.example.miniapp.dtos.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateShopDTO {
    private UUID id;

    private String name;

    private List<UUID> parts;
}

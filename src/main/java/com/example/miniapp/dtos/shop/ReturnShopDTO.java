package com.example.miniapp.dtos.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReturnShopDTO {
    private final UUID id;

    private final String name;

    private final List<UUID> parts;

}

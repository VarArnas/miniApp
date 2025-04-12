package com.example.miniapp.dtos.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class InsertShopDTO {

    private final String name;

    private final List<UUID> parts;
}

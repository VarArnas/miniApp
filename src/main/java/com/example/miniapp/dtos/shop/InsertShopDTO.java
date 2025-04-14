package com.example.miniapp.dtos.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class InsertShopDTO {

    private String name;

    private List<UUID> parts;
}

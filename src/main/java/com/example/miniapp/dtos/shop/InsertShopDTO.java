package com.example.miniapp.dtos.shop;

import com.example.miniapp.entities.CarPart;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class InsertShopDTO {

    private final String name;

    private final List<UUID> parts;
}

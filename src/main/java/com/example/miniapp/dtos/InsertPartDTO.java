package com.example.miniapp.dtos;

import com.example.miniapp.entities.Car;
import com.example.miniapp.entities.MechanicShop;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class InsertPartDTO {

    private final String name;

    private final List<UUID> cars;

    private final UUID mechanicShop;
}

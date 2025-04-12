package com.example.miniapp.dtos.carPart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class InsertPartDTO {

    private final String name;

    private final List<UUID> cars;

    private final UUID mechanicShop;
}

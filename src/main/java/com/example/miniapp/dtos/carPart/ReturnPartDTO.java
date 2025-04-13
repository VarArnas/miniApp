package com.example.miniapp.dtos.carPart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReturnPartDTO {

    private final UUID id;

    private final String name;

    private final List<UUID> cars;

    private Optional<UUID> mechanicShop;
}

package com.example.miniapp.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateCarDTO {

    private final UUID id;

    private final String model;

    private final List<UUID> parts;
}

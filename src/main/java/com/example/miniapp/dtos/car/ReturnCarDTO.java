package com.example.miniapp.dtos.car;

import com.example.miniapp.entities.CarPart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReturnCarDTO {

    private final UUID id;

    private final String model;

    private final List<UUID> parts;
}

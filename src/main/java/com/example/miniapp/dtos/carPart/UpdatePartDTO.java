package com.example.miniapp.dtos.carPart;

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
public class UpdatePartDTO {

    private  UUID id;

    private  String name;

    private  List<UUID> cars;

    private  UUID mechanicShop;

    private Long version;
}

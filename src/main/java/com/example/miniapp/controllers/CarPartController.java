package com.example.miniapp.controllers;

import com.example.miniapp.dtos.carPart.InsertPartDTO;
import com.example.miniapp.dtos.carPart.UpdatePartDTO;
import com.example.miniapp.mappers.CarPartMapper;
import com.example.miniapp.services.CarPartService;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parts")
public class CarPartController {

    private final CarPartService carPartService;
    private final CarPartMapper carPartMapper;

    @GetMapping
    public List<UpdatePartDTO> getAllParts() {
        return carPartService.getAllCarParts().stream()
                .map(carPartMapper::toReturnPartDTONoCars)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdatePartDTO> getPartById(@PathVariable UUID id) {
        return ResponseEntity.ok(carPartMapper.toReturnPartDTO(carPartService.getCarPartByIdWithCars(id)));
    }

    @PostMapping
    public ResponseEntity<Void> createPart(@RequestBody InsertPartDTO insertPartDTO) {
        carPartService.createCarPart(insertPartDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePart(@PathVariable UUID id, @RequestBody UpdatePartDTO updatePartDTO) {
        updatePartDTO.setId(id);
        try{
            carPartService.updateCarPart(updatePartDTO);
        } catch(OptimisticLockException e){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable UUID id) {
        carPartService.deleteCarPart(id);
    }
}

package com.example.miniapp.repositories;

import com.example.miniapp.entities.JPA.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, UUID> { }

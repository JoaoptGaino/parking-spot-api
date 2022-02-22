package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    boolean existsByLicensePlate(String licensePlate);
}

package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.Car;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.UUID;

public interface CarService {
    Car create(Car person);

    Page<Car> findAll(Pageable pageable);

    Car findOne(UUID id);

    Car update(UUID id, Car car);

    void delete(UUID id);
}

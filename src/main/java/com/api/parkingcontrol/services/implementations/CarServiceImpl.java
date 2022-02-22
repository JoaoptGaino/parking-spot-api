package com.api.parkingcontrol.services.implementations;

import com.api.parkingcontrol.models.Car;
import com.api.parkingcontrol.repositories.CarRepository;
import com.api.parkingcontrol.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car person) {
        return null;
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Car findOne(UUID id) {
        return null;
    }

    @Override
    public Car update(UUID id, Car car) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

    private Car existsById(UUID id) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (!carOptional.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Car not found!");
        }

        return carOptional.get();
    }
}

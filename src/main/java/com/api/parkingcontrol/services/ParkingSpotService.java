package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
public class ParkingSpotService {
    final ParkingSpotRepository parkingSpotRepository;


    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if (this.existsByLicensePlateCar(parkingSpot.getLicensePlateCar())) {
            throw new ResponseStatusException(BAD_REQUEST, "License plate already in use!");
        }

        if (this.existsByParkingSpotNumber(parkingSpot.getParkingSpotNumber())) {
            throw new ResponseStatusException(BAD_REQUEST, "Parking spot already in use!");
        }

        if (this.existsByApartmentAndBlock(parkingSpot.getApartment(), parkingSpot.getBlock())) {
            throw new ResponseStatusException(BAD_REQUEST, "Parking Spot already registered for this apartment/block!");
        }

        return parkingSpotRepository.save(parkingSpot);
    }

    public Page<ParkingSpot> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public ParkingSpot findOne(UUID id) {
        ParkingSpot parkingSpot = this.existsById(id);

        return parkingSpot;
    }

    @Transactional
    public void delete(UUID id) {
        ParkingSpot parkingSpot = this.existsById(id);

        parkingSpotRepository.delete(parkingSpot);
    }

    @Transactional
    public ParkingSpot update(UUID id, ParkingSpot parkingSpot) {
        ParkingSpot parkingSpotInDb = this.existsById(id);

        parkingSpot.setId(parkingSpotInDb.getId());
        parkingSpot.setRegistrationDate(parkingSpotInDb.getRegistrationDate());

        return parkingSpotRepository.save(parkingSpot);

    }

    private ParkingSpot existsById(UUID id) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(id);

        if (!parkingSpotOptional.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Parking spot not found!");
        }

        return parkingSpotOptional.get();
    }

    private boolean existsByLicensePlateCar(String licensePlate) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlate);
    }

    private boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    private boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }


}

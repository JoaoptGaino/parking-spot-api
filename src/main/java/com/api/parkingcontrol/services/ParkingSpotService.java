package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ParkingSpotService {

    ParkingSpot create(ParkingSpot parkingSpot);

    Page<ParkingSpot> findAll(Pageable pageable);

    ParkingSpot findOne(UUID id);

    ParkingSpot update(UUID id, ParkingSpot parkingSpot);

    void delete(UUID id);
}

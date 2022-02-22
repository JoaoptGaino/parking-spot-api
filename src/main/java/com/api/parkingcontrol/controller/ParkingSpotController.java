package com.api.parkingcontrol.controller;


import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.services.implementations.ParkingSpotServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("parking-spot")
public class ParkingSpotController {

    final ParkingSpotServiceImpl parkingSpotService;

    public ParkingSpotController(ParkingSpotServiceImpl parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ParkingSpotDto data) {
        ParkingSpot parkingSpot = new ParkingSpot();
        BeanUtils.copyProperties(data, parkingSpot);

        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(CREATED).body(parkingSpotService.create(parkingSpot));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpot>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(OK).body(parkingSpotService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto data) {
        ParkingSpot parkingSpot = new ParkingSpot();
        BeanUtils.copyProperties(data, parkingSpot);

        return ResponseEntity.status(CREATED).body(parkingSpotService.update(id, parkingSpot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        parkingSpotService.delete(id);
        return ResponseEntity.status(OK).body("Parking Spot deleted successfully.");
    }
}

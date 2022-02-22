package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.models.Person;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.UUID;

public interface PersonService {
    Person create(Person person);

    Page<Person> findAll(Pageable pageable);

    Person findOne(UUID id);

    Person update(UUID id, Person person);

    void delete(UUID id);
}

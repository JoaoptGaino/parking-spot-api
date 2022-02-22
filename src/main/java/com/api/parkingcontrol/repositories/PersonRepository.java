package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    boolean existsByApartmentAndBlock(String apartment, String block);
}

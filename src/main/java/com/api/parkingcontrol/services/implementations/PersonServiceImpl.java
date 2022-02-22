package com.api.parkingcontrol.services.implementations;

import com.api.parkingcontrol.models.Car;
import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.models.Person;
import com.api.parkingcontrol.repositories.PersonRepository;
import com.api.parkingcontrol.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Person findOne(UUID id) {
        return null;
    }

    @Override
    public Person update(UUID id, Person person) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

    private Person existsById(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);

        if (!personOptional.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Person not found!");
        }

        return personOptional.get();
    }

    private boolean existsByApartmentAndBlock(String apartment, String block) {
        return personRepository.existsByApartmentAndBlock(apartment, block);
    }
}

package com.example.controller;

import com.example.entities.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> getPersonByCity(@RequestParam("city") String city) {
        return personRepository.findByCityOfLiving(city);
    }

    @GetMapping("/persons/by-age")
    public List<Person> getPersonByCity(@RequestParam("age") int age) {
        return personRepository.findByAgeLessThan(age);
    }

    @GetMapping("/persons/by-name-and-surname")
    public Person getPersonByCity(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        if (personRepository.findFirstByNameAndSurname(name, surname).isPresent()) {
            var person = personRepository.findFirstByNameAndSurname(name, surname).get();
            return person;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person: " + name + " " + surname + " not found");
    }


}
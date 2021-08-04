package com.example.repository;

import com.example.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeLessThan(int age);

    Optional<Person> findFirstByNameAndSurname(String name, String surname);
}

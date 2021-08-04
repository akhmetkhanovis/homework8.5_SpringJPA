package com.example;

import com.example.entities.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
//@EntityScan(basePackages = "com.example")
public class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var names = List.of("Nurlan", "Alexey", "Ivan", "Tambi", "Ilya");
        var surnames = List.of("Saburov", "Scherbakov", "Usovich", "Masaev", "Makarov");
        var cities = List.of("Moskva", "Minsk", "Ufa", "Nalchik");
        var random = new Random();

        IntStream.range(0, 50)
                .forEach(i -> {
                    var person = Person.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(names.size())))
                            .age(random.nextInt(10) + 21)
                            .phoneNumber("+7-999-888-00-" + ((i < 10) ? ("0" + i) : i))
                            .cityOfLiving(cities.get(random.nextInt(cities.size())))
                            .build();

                    personRepository.save(person);
                });

    }
}

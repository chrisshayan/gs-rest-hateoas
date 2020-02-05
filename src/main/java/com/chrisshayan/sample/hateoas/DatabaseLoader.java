package com.chrisshayan.sample.hateoas;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

    CommandLineRunner init(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee(1L, "Darth", "Vader", "Evil"));
            repository.save(new Employee(2L,"Luke", "Walker", "Jedi"));
        };
    }
}

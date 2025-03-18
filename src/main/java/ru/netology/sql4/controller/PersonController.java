package ru.netology.sql4.controller;

import ru.netology.sql4.entity.Person;
import ru.netology.sql4.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Эндпоинт для получения списка людей по городу проживания
    // Пример запроса: GET /persons/by-city?city=Moscow
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return personService.getPersonsByCity(city);
    }

    // Эндпоинт для получения списка людей с возрастом меньше указанного, отсортированных по возрастанию
    // Пример запроса: GET /persons/by-age?age=30
    @GetMapping("/by-age")
    public List<Person> getPersonsByAgeLessThan(@RequestParam("age") int age) {
        return personService.getPersonsByAgeLessThan(age);
    }

    // Эндпоинт для поиска человека по имени и фамилии
    // Пример запроса: GET /persons/by-fullname?name=Ivan&surname=Ivanov
    @GetMapping("/by-fullname")
    public ResponseEntity<Person> getPersonByNameAndSurname(@RequestParam("name") String name,
                                                            @RequestParam("surname") String surname) {
        Optional<Person> personOptional = personService.getPersonByNameAndSurname(name, surname);
        return personOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

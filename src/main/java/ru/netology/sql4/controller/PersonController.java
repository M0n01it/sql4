package ru.netology.sql4.controller;

import ru.netology.sql4.entity.Person;
import ru.netology.sql4.service.PersonService;
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

    // Создание нового Person
    @PostMapping
    public void createPerson(@RequestBody Person person) {
        personService.create(person);
    }

    // Получение всех Person по городу
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return personService.getPersonsByCity(city);
    }

    // Получение всех Person, у которых возраст меньше указанного, с сортировкой по возрастанию
    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam("age") int age) {
        return personService.getPersonsByAgeLessThan(age);
    }

    // Получение Person по имени и фамилии (первое найденное)
    @GetMapping("/by-name-surname")
    public Optional<Person> getPersonByNameAndSurname(@RequestParam("name") String name,
                                                      @RequestParam("surname") String surname) {
        return personService.getPersonByNameAndSurname(name, surname);
    }

    // Обновление данных Person
    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        return personService.update(person);
    }

    // Удаление Person
    @DeleteMapping
    public void deletePerson(@RequestBody Person person) {
        personService.delete(person);
    }
}

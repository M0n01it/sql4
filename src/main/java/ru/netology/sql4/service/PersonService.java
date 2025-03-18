package ru.netology.sql4.service;

import ru.netology.sql4.entity.Person;
import ru.netology.sql4.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Получение списка людей по городу проживания
    public List<Person> getPersonsByCity(String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // Получение списка людей с возрастом меньше указанного, отсортированных по возрастанию возраста
    public List<Person> getPersonsByAgeLessThan(int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    // Поиск человека по имени и фамилии
    public Optional<Person> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    // Дополнительно можно реализовать методы для остальных операций CRUD:
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }
}
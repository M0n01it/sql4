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

    // Получение списка Person по городу
    public List<Person> getPersonsByCity(String city) {
        return personRepository.findByCity(city);
    }

    // Получение списка Person, возраст которых меньше заданного, с сортировкой по возрастанию
    public List<Person> getPersonsByAgeLessThan(int age) {
        return personRepository.findByAgeLessThan(age);
    }

    // Поиск Person по имени и фамилии (возвращается первый найденный элемент)
    public Optional<Person> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    // Создание новой записи Person
    public void create(Person person) {
        personRepository.save(person);
    }

    // Поиск Person по составному ключу
    public Optional<Person> getPerson(String name, String surname, int age) {
        return personRepository.findById(name, surname, age);
    }

    // Обновление записи Person
    public Person update(Person person) {
        return personRepository.update(person);
    }

    // Удаление записи Person
    public void delete(Person person) {
        personRepository.delete(person);
    }
}

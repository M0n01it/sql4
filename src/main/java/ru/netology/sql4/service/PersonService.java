package ru.netology.sql4.service;

import ru.netology.sql4.entity.Person;
import ru.netology.sql4.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonsByCity(String city) {
        return personRepository.findByCityOfLiving(city);
    }
}
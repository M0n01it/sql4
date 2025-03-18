package ru.netology.sql4.repository;


import ru.netology.sql4.entity.Person;
import ru.netology.sql4.entity.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId> {

    // Метод, возвращающий список людей по городу проживания
    List<Person> findByCityOfLiving(String city);

    // Метод, возвращающий список людей, у которых возраст меньше переданного, отсортированный по возрастанию возраста
    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    // Метод, возвращающий Optional с человеком по имени и фамилии
    Optional<Person> findByNameAndSurname(String name, String surname);
}

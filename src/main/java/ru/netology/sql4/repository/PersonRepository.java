package ru.netology.sql4.repository;


import ru.netology.sql4.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    // Метод для поиска пользователей по городу
    List<Person> findByCityOfLiving(String cityOfLiving);
}

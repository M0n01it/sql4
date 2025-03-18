package ru.netology.sql4.repository;


import ru.netology.sql4.entity.Person;
import ru.netology.sql4.entity.PersonId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Создание новой записи
    public void save(Person person) {
        entityManager.persist(person);
    }

    // Поиск записи по составному ключу
    public Optional<Person> findById(String name, String surname, int age) {
        Person person = entityManager.find(Person.class, new PersonId(name, surname, age));
        return Optional.ofNullable(person);
    }

    // Обновление сущности
    public Person update(Person person) {
        return entityManager.merge(person);
    }

    // Удаление сущности
    public void delete(Person person) {
        if (!entityManager.contains(person)) {
            person = entityManager.merge(person);
        }
        entityManager.remove(person);
    }

    // Выборка по городу
    public List<Person> findByCity(String city) {
        String hql = "FROM Person p WHERE p.cityOfLiving = :city";
        TypedQuery<Person> query = entityManager.createQuery(hql, Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }

    // Выборка записей, у которых возраст меньше заданного, с сортировкой по возрастанию
    public List<Person> findByAgeLessThan(int age) {
        String hql = "FROM Person p WHERE p.age < :age ORDER BY p.age ASC";
        TypedQuery<Person> query = entityManager.createQuery(hql, Person.class);
        query.setParameter("age", age);
        return query.getResultList();
    }

    // Выборка первого найденного Person по имени и фамилии с возвратом Optional
    public Optional<Person> findByNameAndSurname(String name, String surname) {
        String hql = "FROM Person p WHERE p.name = :name AND p.surname = :surname";
        TypedQuery<Person> query = entityManager.createQuery(hql, Person.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        List<Person> list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }
}

package ru.netology.sql4.repository;


import ru.netology.sql4.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // HQL-запрос для получения людей по городу
    public List<Person> findByCityOfLiving(String city) {
        String hql = "FROM Person p WHERE p.cityOfLiving = :city";
        TypedQuery<Person> query = entityManager.createQuery(hql, Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}

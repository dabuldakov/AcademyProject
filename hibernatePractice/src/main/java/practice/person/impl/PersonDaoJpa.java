package practice.person.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.person.Person;
import practice.person.PersonDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class PersonDaoJpa implements PersonDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public Person find(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public void update(Person person) {
        entityManager.merge(person);
    }

    @Override
    public ArrayList<Person> updateList(ArrayList<Person> list) {
        return null;
    }

    @Override
    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public ArrayList<Person> createList(ArrayList<Person> list) {
        for (Person person : list) {
            entityManager.persist(person);
        }
        return list;
    }

    @Override
    public void delete(Person person) {
            Person person1 = entityManager.find(Person.class, person.getId());
            entityManager.remove(person1);
    }

    @Override
    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        return null;
    }

    public List<Person> getAllByFirstName(String firstName) {
        TypedQuery<Person> query = entityManager.createNamedQuery("getAllByFirstName", Person.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }
}

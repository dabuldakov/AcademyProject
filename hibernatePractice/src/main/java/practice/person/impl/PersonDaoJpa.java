package practice.person.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.language.Language;
import practice.person.Person;
import practice.person.PersonDao;
import practice.person.PersonException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class PersonDaoJpa implements PersonDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Override
    public Person find(int id) {
        Person person = em.find(Person.class, id);
        em.refresh(person);
        return person;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public void update(Person person) throws PersonException {
        Person foundPerson = em.find(Person.class, person.getId());
        if(foundPerson != null) {
            em.merge(person);
        }
        else
            throw new PersonException("Person not found, use POST to create.");
    }

    @Override
    public ArrayList<Person> updateList(ArrayList<Person> list) {
        return null;
    }

    @Override
    public Person create(Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public ArrayList<Person> createList(ArrayList<Person> list) {
        for (Person person : list) {
            em.persist(person);
        }
        return list;
    }

    @Override
    public void delete(Person person) {
            Person person1 = em.find(Person.class, person.getId());
            em.remove(person1);
    }

    @Override
    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        return null;
    }

    public List<Person> getAllByFirstName(String firstName) {
        TypedQuery<Person> query = em.createNamedQuery("getAllByFirstName", Person.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }
}

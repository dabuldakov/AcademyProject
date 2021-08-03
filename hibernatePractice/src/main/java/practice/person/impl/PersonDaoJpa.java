package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.mapper.Mapper;
import practice.person.Person;
import practice.person.PersonDao;
import practice.person.PersonDto;
import practice.person.PersonRepository;
import practice.person.exception.PersonNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class PersonDaoJpa implements PersonDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Autowired
    private PersonRepository repository;

    @Autowired
    Mapper mapper;

    @Override
    public Person find(int id) throws PersonNotFoundException {
        Person person = em.find(Person.class, id);
        if(person == null)
            throw new PersonNotFoundException("Id: " + id);
        em.refresh(person);
        return person;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> getAllByFirstName(String firstName) {
        TypedQuery<Person> query = em.createNamedQuery("getAllByFirstName", Person.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public void update(Person person) throws PersonNotFoundException {
        Person foundPerson = em.find(Person.class, person.getId());
        if(foundPerson != null)
            em.merge(person);
        else
            throw new PersonNotFoundException("Id: " + person.getId());
    }



    @Override
    public Person create(Person person) {
        em.persist(person);
        return person;
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

    @Override
    public ArrayList<Person> updateList(ArrayList<Person> list) {
        return null;
    }


}

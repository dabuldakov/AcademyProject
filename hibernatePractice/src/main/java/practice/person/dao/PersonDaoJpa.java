package practice.person.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.exception.NotFoundException;
import practice.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class PersonDaoJpa implements PersonDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Autowired
    private PersonRepository repository;

    @Override
    public Person find(int id) {
        Person person = em.find(Person.class, id);
        if (person == null)
            throw new NotFoundException();
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
    public void update(Person person) {
        if (repository.existsById(person.getId()))
            em.merge(person);
        else
            throw new NotFoundException();
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

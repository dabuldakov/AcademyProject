package practice.person;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Component
@Transactional
public class PersonDAOJpa implements PersonDAO{

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public Person find(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public boolean update(Person person) {
        try {
            entityManager.merge(person);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
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
        return null;
    }

    @Override
    public boolean delete(Person person) {
        try{
            Person person1 = entityManager.find(Person.class, person.getId());
            entityManager.remove(person1);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        return null;
    }
}

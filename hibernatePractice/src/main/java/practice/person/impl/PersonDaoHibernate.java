package practice.person.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import practice.person.Person;
import practice.person.PersonDao;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDaoHibernate implements PersonDao {

    SessionFactory sessionFactory;

    public PersonDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    public Person find(int id) {
        return sessionFactory.openSession().get(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    public void update(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();

    }

    @Override
    public ArrayList<Person> updateList(ArrayList<Person> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        return null;
    }

    public void delete(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(person);
        transaction.commit();
    }

    @Override
    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        return null;
    }

    @Override
    public List<Person> getAllByFirstName(String firstName) {
        return null;
    }

    public Person create(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        try {
            transaction.commit();
            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

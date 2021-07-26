package practice.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOHibernate implements PersonDAO {

    SessionFactory sessionFactory;

    public PersonDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.openSession();
    }

    public Person find(int id) {
        return sessionFactory.openSession().get(Person.class, id);
    }

    public boolean update(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        try {
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Person> updateList(ArrayList<Person> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        return null;
    }

    public boolean delete(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(person);
        try {
            transaction.commit();
            return true;
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        return null;
    }

    @Override
    public List<Person> getAllByFirstName() {
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

    @Override
    public ArrayList<Person> createList(ArrayList<Person> list) {
        return null;
    }

}

package practice.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.utils.HibernateSessionFactoryUtil;

import javax.persistence.OptimisticLockException;

@Component
public class PersonDAO {

    SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.sessionFactory = hibernateSessionFactoryUtil.getSessionFactory();
    }

    public Person findPerson(int id) {
        return sessionFactory.openSession().get(Person.class, id);
    }

    public Person updatePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
        return findPerson(person.getId());
    }

    public boolean deletePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(person);
        try {
            transaction.commit();
        } catch (OptimisticLockException e) {
            return false;
        }
        return true;
    }

    public boolean savePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        try {
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

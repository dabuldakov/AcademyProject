package practice;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.OptimisticLockException;

public class PersonDAO {

    public Person findPerson(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Person.class, id);
    }

    public Person updatePerson(Person person) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
        return findPerson(person.getId());
    }

    public boolean deletePerson(Person person) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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

package practice.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import practice.department.Department;
import practice.person.Person;

@Component
public class HibernateSessionFactoryUtil {

    private SessionFactory sessionFactory;

    public HibernateSessionFactoryUtil() {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Department.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}

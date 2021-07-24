package practice.department;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.utils.HibernateSessionFactoryUtil;

@Component
public class DepartmentDAO {
    SessionFactory sessionFactory;

    @Autowired
    public DepartmentDAO(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.sessionFactory = hibernateSessionFactoryUtil.getSessionFactory();
    }
}

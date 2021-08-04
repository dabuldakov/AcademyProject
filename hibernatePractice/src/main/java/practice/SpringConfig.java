package practice;

import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import practice.department.Department;
import practice.department.DepartmentDto;
import practice.document.Document;
import practice.document.DocumentDto;
import practice.language.Language;
import practice.language.LanguageDto;
import practice.mapper.Mapper;
import practice.person.Person;
import practice.person.PersonDto;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Properties;

@Configuration
@PropertySource("classpath:persistence.properties")
@ComponentScan("practice")
@EnableTransactionManagement
@EnableJpaRepositories("practice")
@BatchSize(size = 5)
public class SpringConfig {

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String driver,
                                 @Value("${jdbc.url}") String url,
                                 @Value("${jdbc.user}") String user,
                                 @Value("${jdbc.password}") String password,
                                 @Value("${jdbc.schema}") String schema) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setSchema(schema);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean factoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        Class<?>[] classes = new Class[4];
        classes[0] = Person.class;
        classes[1] = Department.class;
        classes[2] = Document.class;
        classes[3] = Language.class;
        sessionFactory.setAnnotatedClasses(classes);
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("practice.person", "practice.department", "practice.document", "practice.language");
        entityManagerFactoryBean.afterPropertiesSet();
        Properties properties = new Properties();
        properties.put("hibernate.jdbc.batch_size", "5");
        properties.put("hibernate.hbm2ddl.auto", "create");
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Mapper mapper(){
        ArrayList<Class<?>> listClass = new ArrayList<>();
        listClass.add(Document.class);
        listClass.add(DocumentDto.class);
        listClass.add(Department.class);
        listClass.add(DepartmentDto.class);
        listClass.add(Language.class);
        listClass.add(LanguageDto.class);
        listClass.add(Person.class);
        listClass.add(PersonDto.class);
        return new Mapper(listClass);
    }
}

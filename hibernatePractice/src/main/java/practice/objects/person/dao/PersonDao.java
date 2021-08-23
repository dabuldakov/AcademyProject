package practice.objects.person.dao;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Component;
import practice.objects.person.Person;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface PersonDao {
    Person find(int id);
    List<Person> findAll();
    List<Person> getAllByFirstName(String firstName);

    Person create(Person person);
    void update(Person person);
    void delete(Person person);

    ArrayList<Person> updateList(ArrayList<Person> list);
    ArrayList<Person> deleteList(ArrayList<Person> list);
}

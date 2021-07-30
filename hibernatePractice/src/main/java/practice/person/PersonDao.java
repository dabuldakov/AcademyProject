package practice.person;

import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface PersonDao {
    public Person find(int id);

    public List<Person> findAll();

    public void update(Person person);

    public ArrayList<Person> updateList(ArrayList<Person> list);

    public Person create(Person person);

    public ArrayList<Person> createList(ArrayList<Person> list);

    public void delete(Person person);

    public ArrayList<Person> deleteList(ArrayList<Person> list);

    public List<Person> getAllByFirstName(String firstName);
}

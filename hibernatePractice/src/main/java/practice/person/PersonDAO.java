package practice.person;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface PersonDAO {
    public Person find(int id);

    public boolean update(Person person);

    public ArrayList<Person> updateList(ArrayList<Person> list);

    public Person create(Person person);

    public ArrayList<Person> createList(ArrayList<Person> list);

    public boolean delete(Person person);

    public ArrayList<Person> deleteList(ArrayList<Person> list);

    public List<Person> getAllByFirstName();
}

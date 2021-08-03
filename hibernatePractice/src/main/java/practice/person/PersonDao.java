package practice.person;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Component;
import practice.person.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface PersonDao {
    public Person find(int id) throws PersonNotFoundException;
    public List<Person> findAll();
    public List<Person> getAllByFirstName(String firstName);

    public Person create(Person person);
    public void update(Person person) throws PersonNotFoundException;
    public void delete(Person person);

    public ArrayList<Person> updateList(ArrayList<Person> list);
    public ArrayList<Person> deleteList(ArrayList<Person> list);
}

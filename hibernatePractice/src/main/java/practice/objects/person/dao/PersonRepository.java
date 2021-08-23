package practice.objects.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.objects.person.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByFirstName(String firstName);
    List<Person> findAllByFirstName(String firstName);
}

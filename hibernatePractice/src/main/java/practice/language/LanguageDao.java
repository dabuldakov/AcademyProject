package practice.language;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Component;
import practice.person.Person;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface LanguageDao {
    public Language find(int id);

    public List<Language> findAll();

    public void update(Language language);

    public ArrayList<Language> updateList(ArrayList<Language> list);

    public Language create(Language language);

    public ArrayList<Language> createList(ArrayList<Language> list);

    public void delete(Language language);

    public ArrayList<Language> deleteList(ArrayList<Person> list);
}

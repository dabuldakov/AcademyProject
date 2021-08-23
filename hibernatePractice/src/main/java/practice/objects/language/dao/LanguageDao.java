package practice.objects.language.dao;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Component;
import practice.objects.language.Language;
import practice.objects.person.Person;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface LanguageDao {
    Language find(int id);
    List<Language> findAll();

    Language create(Language language);
    void update(Language language);
    void delete(Language language);

    ArrayList<Language> createList(ArrayList<Language> list);
    ArrayList<Language> updateList(ArrayList<Language> list);
    ArrayList<Language> deleteList(ArrayList<Person> list);
}

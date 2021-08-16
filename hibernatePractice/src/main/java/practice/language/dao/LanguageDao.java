package practice.language.dao;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Component;
import practice.exception.NotFoundException;
import practice.language.model.Language;
import practice.person.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
@BatchSize(size = 5)
public interface LanguageDao {
    Language find(int id);
    List<Language> findAll();

    Language create(Language language);
    void update(Language language) throws NotFoundException;
    void delete(Language language);

    ArrayList<Language> createList(ArrayList<Language> list);
    ArrayList<Language> updateList(ArrayList<Language> list);
    ArrayList<Language> deleteList(ArrayList<Person> list);
}

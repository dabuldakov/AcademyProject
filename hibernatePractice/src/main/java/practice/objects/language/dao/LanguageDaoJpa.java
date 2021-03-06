package practice.objects.language.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.exception.NotFoundException;
import practice.objects.language.Language;
import practice.objects.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class LanguageDaoJpa implements LanguageDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Autowired
    LanguageRepository repository;

    @Override
    public Language find(int id) {
        Language language = em.find(Language.class, id);
        if (language == null) {
            throw new NotFoundException();
        }
        return language;
    }

    @Override
    public List<Language> findAll() {
        return repository.findAll();
    }

    @Override
    public Language create(Language language) {
        em.persist(language);
        return language;
    }

    @Override
    public void update(Language language){
        if (repository.existsById(language.getId()))
            em.merge(language);
        else throw new NotFoundException();
    }

    @Override
    public void delete(Language language) {
        Language language1 = em.find(Language.class, language.getId());
        em.remove(language1);
    }

    @Override
    public ArrayList<Language> createList(ArrayList<Language> list) {
        return null;
    }

    @Override
    public ArrayList<Language> updateList(ArrayList<Language> list) {
        return null;
    }

    @Override
    public ArrayList<Language> deleteList(ArrayList<Person> list) {
        return null;
    }
}

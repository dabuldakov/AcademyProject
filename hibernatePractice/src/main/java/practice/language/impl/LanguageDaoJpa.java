package practice.language.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.language.Language;
import practice.language.LanguageDao;
import practice.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class LanguageDaoJpa implements LanguageDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Override
    public Language find(int id) {
        return em.find(Language.class, id);
    }

    @Override
    public List<Language> findAll() {
        return null;
    }

    @Override
    public void update(Language language) {
        em.merge(language);
    }

    @Override
    public ArrayList<Language> updateList(ArrayList<Language> list) {
        return null;
    }

    @Override
    public Language create(Language language) {
        em.persist(language);
        return language;
    }

    @Override
    public ArrayList<Language> createList(ArrayList<Language> list) {
        return null;
    }

    @Override
    public void delete(Language language) {
        Language language1 = em.find(Language.class, language.getId());
        em.remove(language1);
    }

    @Override
    public ArrayList<Language> deleteList(ArrayList<Person> list) {
        return null;
    }
}

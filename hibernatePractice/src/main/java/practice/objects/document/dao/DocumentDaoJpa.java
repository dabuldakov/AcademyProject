package practice.objects.document.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.exception.NotFoundException;
import practice.objects.document.Document;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DocumentDaoJpa implements DocumentDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Autowired
    DocumentRepository repository;

    @Override
    public Document find(int id) {
        Document document = em.find(Document.class, id);
        if (document == null)
            throw new NotFoundException();
        em.refresh(document);
        return document;
    }

    @Override
    public List<Document> findAll() {
        return repository.findAll();
    }

    @Override
    public Document create(Document document) {
        em.persist(document);
        return document;
    }

    @Override
    public void update(Document document) {
        if (repository.existsById(document.getId()))
            em.merge(document);
        else throw new NotFoundException();
    }

    @Override
    public void delete(Document document) {
        Document document1 = em.find(Document.class, document.getId());
        em.remove(document1);
    }

    @Override
    public ArrayList<Document> updateList(ArrayList<Document> list) {
        return null;
    }

    @Override
    public ArrayList<Document> createList(ArrayList<Document> list) {
        return null;
    }

    @Override
    public ArrayList<Document> deleteList(ArrayList<Document> list) {
        return null;
    }
}

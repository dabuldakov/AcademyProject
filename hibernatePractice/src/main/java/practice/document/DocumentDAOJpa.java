package practice.document;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Component
@Transactional
public class DocumentDAOJpa implements DocumentDAO{

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public Document find(int id) {
        return null;
    }

    @Override
    public boolean update(Document document) {
        return false;
    }

    @Override
    public ArrayList<Document> updateList(ArrayList<Document> list) {
        return null;
    }

    @Override
    public Document create(Document document) {
        entityManager.persist(document);
        return document;
    }

    @Override
    public void createList(ArrayList<Document> list) {

    }

    @Override
    public boolean delete(Document document) {
        return false;
    }

    @Override
    public ArrayList<Document> deleteList(ArrayList<Document> list) {
        return null;
    }
}

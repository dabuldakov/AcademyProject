package practice.document.dao;

import practice.document.model.Document;
import practice.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface DocumentDao {
    public Document find(int id);
    public List<Document> findAll();

    public Document create(Document document);
    public void update(Document document) throws NotFoundException;
    public void delete(Document document);

    public ArrayList<Document> createList(ArrayList<Document> list);
    public ArrayList<Document> updateList(ArrayList<Document> list);
    public ArrayList<Document> deleteList(ArrayList<Document> list);
}

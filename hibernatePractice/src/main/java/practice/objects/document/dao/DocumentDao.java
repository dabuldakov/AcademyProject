package practice.objects.document.dao;

import practice.objects.document.Document;

import java.util.ArrayList;
import java.util.List;

public interface DocumentDao {
    public Document find(int id);
    public List<Document> findAll();

    public Document create(Document document);
    public void update(Document document);
    public void delete(Document document);

    public ArrayList<Document> createList(ArrayList<Document> list);
    public ArrayList<Document> updateList(ArrayList<Document> list);
    public ArrayList<Document> deleteList(ArrayList<Document> list);
}

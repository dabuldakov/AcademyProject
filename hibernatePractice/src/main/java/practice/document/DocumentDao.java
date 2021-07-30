package practice.document;

import java.util.ArrayList;

public interface DocumentDao {
    public Document find(int id);

    public void update(Document document);

    public ArrayList<Document> updateList(ArrayList<Document> list);

    public Document create(Document document);

    public void createList(ArrayList<Document> list);

    public void delete(Document document);

    public ArrayList<Document> deleteList(ArrayList<Document> list);
}

package practice.document;

import java.util.ArrayList;

public interface DocumentDAO {
    public Document find(int id);

    public boolean update(Document document);

    public ArrayList<Document> updateList(ArrayList<Document> list);

    public Document create(Document document);

    public void createList(ArrayList<Document> list);

    public boolean delete(Document document);

    public ArrayList<Document> deleteList(ArrayList<Document> list);
}

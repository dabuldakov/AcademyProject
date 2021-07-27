package practice.document;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DocumentService implements practice.Service {

    DocumentDAO documentDAO;

    public DocumentService(@Qualifier("documentDAOJpa") DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public void create(){
        Document document = new Document();
        document.setNumber("111-sssd-2324-dgdg");
        Document document1 = documentDAO.create(document);
        System.out.println(document1);
    }

    @Override
    public void createList() {

    }
}

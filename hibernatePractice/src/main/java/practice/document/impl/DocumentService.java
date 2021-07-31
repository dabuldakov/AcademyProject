package practice.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.document.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService implements practice.document.DocumentService {

    @Autowired
    @Qualifier("documentDaoJpa")
    private DocumentDao dao;

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DocumentConverter converter;

    @Override
    public DocumentDto find(int id) {
        return converter.toDocumentDto(dao.find(id));
    }

    @Override
    public List<DocumentDto> findAll() {
        return repository.findAll().stream()
                .map(x -> converter.toDocumentDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DocumentDto dto) {
        dao.update(converter.toDocument(dto));
    }

    @Override
    public DocumentDto create(DocumentDto dto) {
        Document document = dao.create(converter.toDocument(dto));
        return converter.toDocumentDto(document);
    }

    @Override
    public void delete(DocumentDto dto) {
        dao.delete(converter.toDocument(dto));
    }
}

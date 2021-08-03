package practice.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.document.*;
import practice.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    @Qualifier("documentDaoJpa")
    private DocumentDao dao;

    @Autowired
    Mapper mapper;

    @Override
    public DocumentDto find(int id) {
        return mapper.run(dao.find(id), DocumentDto.class);
    }

    @Override
    public List<DocumentDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.run(x, DocumentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DocumentDto dto) {
        dao.update(mapper.run(dto, Document.class));
    }

    @Override
    public DocumentDto create(DocumentDto dto) {
        Document document = dao.create(mapper.run(dto, Document.class));
        return mapper.run(document, DocumentDto.class);
    }

    @Override
    public void delete(DocumentDto dto) {
        dao.delete(mapper.run(dto, Document.class));
    }
}

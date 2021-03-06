package practice.objects.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.mapper.Mapper;
import practice.objects.document.Document;
import practice.objects.document.DocumentDto;
import practice.objects.document.dao.DocumentDao;

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
        return mapper.convert(dao.find(id), DocumentDto.class);
    }

    @Override
    public List<DocumentDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.convert(x, DocumentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DocumentDto dto){
        dao.update(mapper.convert(dto, Document.class));
    }

    @Override
    public DocumentDto create(DocumentDto dto) {
        Document document = dao.create(mapper.convert(dto, Document.class));
        return mapper.convert(document, DocumentDto.class);
    }

    @Override
    public void delete(DocumentDto dto) {
        dao.delete(mapper.convert(dto, Document.class));
    }
}

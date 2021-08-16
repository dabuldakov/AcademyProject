package practice.document.service;

import practice.document.model.DocumentDto;
import practice.exception.NotFoundException;

import java.util.List;

public interface DocumentService {
    public DocumentDto find(int id);
    public List<DocumentDto> findAll();
    public void update(DocumentDto dto) throws NotFoundException;
    public DocumentDto create(DocumentDto dto);
    public void delete(DocumentDto dto);
}

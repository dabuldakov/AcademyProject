package practice.objects.document.service;

import practice.objects.document.DocumentDto;

import java.util.List;

public interface DocumentService {
    public DocumentDto find(int id);
    public List<DocumentDto> findAll();
    public void update(DocumentDto dto);
    public DocumentDto create(DocumentDto dto);
    public void delete(DocumentDto dto);
}

package practice.document;

import practice.NotFoundException;

import javax.validation.Valid;
import java.util.List;

public interface DocumentService {
    public DocumentDto find(int id);
    public List<DocumentDto> findAll();
    public void update(@Valid DocumentDto dto) throws NotFoundException;
    public DocumentDto create(@Valid DocumentDto dto);
    public void delete(@Valid DocumentDto dto);
}

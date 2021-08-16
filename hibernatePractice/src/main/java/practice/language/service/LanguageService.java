package practice.language.service;

import practice.exception.NotFoundException;
import practice.language.model.LanguageDto;

import java.util.List;

public interface LanguageService {
    public LanguageDto find(int id);
    public List<LanguageDto> findAll();
    public void update(LanguageDto languageDto) throws NotFoundException;
    public LanguageDto create(LanguageDto languageDto);
    public void delete(LanguageDto languageDto);
}

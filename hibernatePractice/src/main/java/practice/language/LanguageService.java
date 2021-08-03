package practice.language;

import practice.NotFoundException;
import practice.person.PersonDto;

import java.util.List;

public interface LanguageService {
    public LanguageDto find(int id);
    public List<LanguageDto> findAll();
    public void update(LanguageDto languageDto) throws NotFoundException;
    public LanguageDto create(LanguageDto languageDto);
    public void delete(LanguageDto languageDto);
}

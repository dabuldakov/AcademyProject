package practice.language;

import practice.person.PersonDto;

import java.util.List;

public interface LanguageService {
    public LanguageDto find(int id);
    public List<LanguageDto> findAll();
    public void update(LanguageDto languageDto);
    public LanguageDto create(LanguageDto languageDto);
    public void delete(LanguageDto languageDto);
}

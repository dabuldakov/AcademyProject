package practice.language;

import practice.exception.NotFoundException;

import javax.validation.Valid;
import java.util.List;

public interface LanguageService {
    public LanguageDto find(int id);
    public List<LanguageDto> findAll();
    public void update(@Valid LanguageDto languageDto) throws NotFoundException;
    public LanguageDto create(@Valid LanguageDto languageDto);
    public void delete(@Valid LanguageDto languageDto);
}

package practice.language.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.language.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService implements practice.language.LanguageService {
    @Autowired
    @Qualifier("languageDaoJpa")
    LanguageDao dao;

    @Autowired
    LanguageRepository repository;

    @Autowired
    LanguageConverter converter;

    public LanguageDto find(int id) {
        return converter.toLanguageDto(dao.find(id));
    }

    @Override
    public List<LanguageDto> findAll() {
        return repository.findAll().stream()
                .map(x -> converter.toLanguageDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public void update(LanguageDto languageDto) {
        dao.update(converter.toLanguage(languageDto));
    }

    @Override
    public LanguageDto create(LanguageDto languageDto) {
        Language language = dao.create(converter.toLanguage(languageDto));
        return converter.toLanguageDto(language);
    }

    @Override
    public void delete(LanguageDto languageDto) {
        dao.delete(converter.toLanguage(languageDto));
    }

}

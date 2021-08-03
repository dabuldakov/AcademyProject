package practice.language.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.language.*;
import practice.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    @Qualifier("languageDaoJpa")
    LanguageDao dao;

    @Autowired
    Mapper mapper;

    public LanguageDto find(int id) {
        return mapper.run(dao.find(id), LanguageDto.class);
    }

    @Override
    public List<LanguageDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.run(x, LanguageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(LanguageDto languageDto) {
        dao.update(mapper.run(languageDto, Language.class));
    }

    @Override
    public LanguageDto create(LanguageDto languageDto) {
        Language language = dao.create(mapper.run(languageDto, Language.class));
        return mapper.run(language, LanguageDto.class);
    }

    @Override
    public void delete(LanguageDto languageDto) {
        dao.delete(mapper.run(languageDto, Language.class));
    }

}

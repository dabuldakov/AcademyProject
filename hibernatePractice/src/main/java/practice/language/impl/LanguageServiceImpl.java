package practice.language.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import practice.NotFoundException;
import practice.language.*;
import practice.mapper.Mapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
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
    public void update(@Valid LanguageDto languageDto) throws NotFoundException {
        dao.update(mapper.run(languageDto, Language.class));
    }

    @Override
    public LanguageDto create(@Valid LanguageDto languageDto) {
        Language language = dao.create(mapper.run(languageDto, Language.class));
        return mapper.run(language, LanguageDto.class);
    }

    @Override
    public void delete(@Valid LanguageDto languageDto) {
        dao.delete(mapper.run(languageDto, Language.class));
    }

}

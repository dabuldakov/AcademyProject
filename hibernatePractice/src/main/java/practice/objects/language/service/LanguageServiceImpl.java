package practice.objects.language.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.mapper.Mapper;
import practice.objects.language.Language;
import practice.objects.language.LanguageDto;
import practice.objects.language.dao.LanguageDao;

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
        return mapper.convert(dao.find(id), LanguageDto.class);
    }

    @Override
    public List<LanguageDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.convert(x, LanguageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(LanguageDto languageDto){
        dao.update(mapper.convert(languageDto, Language.class));
    }

    @Override
    public LanguageDto create(LanguageDto languageDto) {
        Language language = dao.create(mapper.convert(languageDto, Language.class));
        return mapper.convert(language, LanguageDto.class);
    }

    @Override
    public void delete(LanguageDto languageDto) {
        dao.delete(mapper.convert(languageDto, Language.class));
    }

}

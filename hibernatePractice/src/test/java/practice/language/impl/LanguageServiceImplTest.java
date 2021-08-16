package practice.language.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.Utils;
import practice.language.model.Language;
import practice.language.model.LanguageDto;
import practice.language.dao.LanguageDaoJpa;
import practice.language.service.LanguageServiceImpl;
import practice.mapper.Mapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceImplTest {

    @InjectMocks
    LanguageServiceImpl service;
    @Mock
    Mapper mapper;
    @Mock
    LanguageDaoJpa languageDaoJpa;
    Language language;
    LanguageDto languageDto;
    List<Language> languageList;
    List<LanguageDto> languageDtoList;

    @Test
    void findShouldReturnLanguage() {
        //given
        language = Utils.createLanguageRussia();
        languageDto = Utils.createLanguageDtoRussia();
        //when
        Mockito.when(languageDaoJpa.find(1)).thenReturn(language);
        Mockito.when(mapper.convert(language, LanguageDto.class)).thenReturn(languageDto);
        LanguageDto languageDto = service.find(1);
        //then
        Mockito.verify(languageDaoJpa).find(1);
        Mockito.verify(mapper).convert(language, LanguageDto.class);
        assertEquals(language.getName(), languageDto.getName());
    }

    @Test
    void findAllShouldReturnLanguageDtoList() {
        //given
        languageList = Utils.createLanguageList();
        languageDtoList = Utils.createLanguageDtoList();
        //when
        Mockito.when(mapper.convert(languageList.get(0), LanguageDto.class)).thenReturn(languageDtoList.get(0));
        Mockito.when(mapper.convert(languageList.get(1), LanguageDto.class)).thenReturn(languageDtoList.get(1));
        Mockito.when(languageDaoJpa.findAll()).thenReturn(languageList);
        List<LanguageDto> languageDtoListResult = service.findAll();
        //then
        Mockito.verify(languageDaoJpa).findAll();
        Mockito.verify(mapper).convert(languageList.get(0), LanguageDto.class);
        Mockito.verify(mapper).convert(languageList.get(1), LanguageDto.class);
        for (int i = 0; i < 2; i++) {
            assertEquals(languageList.get(i).getName(), languageDtoListResult.get(i).getName());
        }
    }

    @Test
    void updateShouldPerformUpdateMethod() {
        //give
        language = Utils.createLanguageRussia();
        languageDto = Utils.createLanguageDtoRussia();
        //when
        Mockito.when(mapper.convert(languageDto, Language.class)).thenReturn(language);
        service.update(languageDto);
        //then
        Mockito.verify(mapper).convert(languageDto, Language.class);
        Mockito.verify(languageDaoJpa).update(language);
    }

    @Test
    void create() {
        //given
        language = Utils.createLanguageRussia();
        languageDto = Utils.createLanguageDtoRussia();
        //when
        Mockito.when(mapper.convert(languageDto, Language.class)).thenReturn(language);
        Mockito.when(languageDaoJpa.create(language)).thenReturn(language);
        Mockito.when(mapper.convert(language, LanguageDto.class)).thenReturn(languageDto);
        LanguageDto languageDtoResult = service.create(this.languageDto);
        //then
        Mockito.verify(mapper).convert(languageDto, Language.class);
        Mockito.verify(mapper).convert(language, LanguageDto.class);
        Mockito.verify(languageDaoJpa).create(language);
        assertEquals(languageDto.getName(), languageDtoResult.getName());
        assertTrue(languageDtoResult.getId() > 0);
    }

    @Test
    void delete() {
        //given
        language = Utils.createLanguageRussia();
        languageDto = Utils.createLanguageDtoRussia();
        //when
        Mockito.when(mapper.convert(languageDto, Language.class)).thenReturn(language);
        service.delete(languageDto);
        //then
        Mockito.verify(mapper).convert(languageDto, Language.class);
        Mockito.verify(languageDaoJpa).delete(language);
    }
}
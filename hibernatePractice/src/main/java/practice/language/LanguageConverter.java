package practice.language;

import org.springframework.stereotype.Component;

@Component
public class LanguageConverter {
    public Language toLanguage(LanguageDto languageDto){
        Language language = new Language();
        language.setId(languageDto.getId());
        language.setName(languageDto.getName());
        return language;
    }

    public LanguageDto toLanguageDto(Language language){
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(language.getId());
        languageDto.setName(language.getName());
        return languageDto;
    }
}

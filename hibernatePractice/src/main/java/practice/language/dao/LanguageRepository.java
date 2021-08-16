package practice.language.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.language.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}

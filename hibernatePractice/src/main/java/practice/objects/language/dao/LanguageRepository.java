package practice.objects.language.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.objects.language.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}

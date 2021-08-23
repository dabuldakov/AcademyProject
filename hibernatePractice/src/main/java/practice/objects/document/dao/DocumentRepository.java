package practice.objects.document.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.objects.document.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}

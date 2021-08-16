package practice.document.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.document.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}

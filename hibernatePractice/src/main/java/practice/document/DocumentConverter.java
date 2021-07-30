package practice.document;

import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {
    public Document toDocument(DocumentDto dto){
        Document document = new Document();
        document.setId(dto.getId());
        document.setNumber(dto.getNumber());
        document.setExpiryDate(dto.getExpiryDate());
        return document;
    }

    public DocumentDto toDocumentDto(Document document){
        DocumentDto dto = new DocumentDto();
        dto.setId(document.getId());
        dto.setNumber(document.getNumber());
        dto.setExpiryDate(document.getExpiryDate());
        return dto;
    }
}

package practice.objects.document.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.Utils;
import practice.mapper.Mapper;
import practice.objects.document.Document;
import practice.objects.document.DocumentDto;
import practice.objects.document.dao.DocumentDao;
import practice.objects.document.service.DocumentServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @InjectMocks
    DocumentServiceImpl service;
    @Mock
    DocumentDao dao;
    @Mock
    Mapper mapper;
    DocumentDto documentDto;
    Document document;
    List<DocumentDto> documentDtoList;
    List<Document> documentList;

    @Test
    void findShouldReturnDocument() {
        //given
        documentDto = Utils.createDocumentDtoOne();
        document = Utils.createDocumentOne();
        //when
        Mockito.when(dao.find(1)).thenReturn(document);
        Mockito.when(mapper.convert(document, DocumentDto.class)).thenReturn(documentDto);
        DocumentDto result = service.find(1);
        //then
        assertEquals(documentDto.getNumber(), result.getNumber());
        Mockito.verify(dao).find(1);
        Mockito.verify(mapper).convert(document, DocumentDto.class);
    }

    @Test
    void findAllShouldReturnDocumentList() {
        //given
        documentDtoList = Utils.createDocumentDtoList();
        documentList = Utils.createDocumentList();
        //when
        Mockito.when(mapper.convert(documentList.get(0), DocumentDto.class)).thenReturn(documentDtoList.get(0));
        Mockito.when(mapper.convert(documentList.get(1), DocumentDto.class)).thenReturn(documentDtoList.get(1));
        Mockito.when(mapper.convert(documentList.get(2), DocumentDto.class)).thenReturn(documentDtoList.get(2));
        Mockito.when(dao.findAll()).thenReturn(documentList);
        List<DocumentDto> result = service.findAll();
        //then
        Mockito.verify(dao).findAll();
        Mockito.verify(mapper).convert(documentList.get(0), DocumentDto.class);
        Mockito.verify(mapper).convert(documentList.get(1), DocumentDto.class);
        Mockito.verify(mapper).convert(documentList.get(2), DocumentDto.class);
        assertEquals(documentDtoList.size(), result.size());
        assertEquals(documentDtoList.get(0), result.get(0));
        assertEquals(documentDtoList.get(1), result.get(1));
        assertEquals(documentDtoList.get(2), result.get(2));
    }

    @Test
    void updateShouldPerformedDaoUpdate() {
        //given
        documentDto = Utils.createDocumentDtoOne();
        document = Utils.createDocumentOne();
        //when
        Mockito.when(mapper.convert(documentDto, Document.class)).thenReturn(document);
        service.update(documentDto);
        //then
        Mockito.verify(mapper).convert(documentDto, Document.class);
        Mockito.verify(dao).update(document);
    }

    @Test
    void createShouldPerformedDaoCreate() {
        //given
        document = Utils.createDocumentOne();
        documentDto = Utils.createDocumentDtoOne();
        //when
        Mockito.when(mapper.convert(documentDto, Document.class)).thenReturn(document);
        Mockito.when(dao.create(document)).thenReturn(document);
        Mockito.when(mapper.convert(document, DocumentDto.class)).thenReturn(documentDto);
        DocumentDto result = service.create(this.documentDto);
        //then
        assertEquals(documentDto.getNumber(), result.getNumber());
        Mockito.verify(dao).create(document);
        Mockito.verify(mapper).convert(documentDto, Document.class);
        Mockito.verify(mapper).convert(document, DocumentDto.class);
    }

    @Test
    void deleteShouldPerformedDaoDelete() {
        //given
        documentDto = Utils.createDocumentDtoOne();
        document = Utils.createDocumentOne();
        //when
        Mockito.when(mapper.convert(documentDto, Document.class)).thenReturn(document);
        service.delete(documentDto);
        //then
        Mockito.verify(mapper).convert(documentDto, Document.class);
        Mockito.verify(dao).delete(document);
    }
}
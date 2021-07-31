package practice.mapper;

import org.junit.jupiter.api.Test;
import practice.document.Document;
import practice.document.DocumentDto;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void run() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Document document = new Document();
        document.setId(15);
        document.setNumber("aaa-sss-222");
        document.setExpiryDate(new Date());

        Mapper mapper = new Mapper();
        DocumentDto documentDto = mapper.run(document, DocumentDto.class);
        System.out.println(document);
        System.out.println(documentDto);
    }
}
package practice.mapper;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import practice.mapper.model.Car;
import practice.mapper.model.Doc;
import practice.mapper.model.DocDto;
import practice.mapper.model.Human;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

class MapperTest {

    Mapper mapper;

    @BeforeEach
    public void before(){
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(Doc.class);
        classes.add(DocDto.class);
        mapper = new Mapper(classes);
    }

    @Test
    void shouldReturnNewMappedObject() {
        //Given
        Doc doc = createDocData();
        ModelMapper modelMapper = modelMapper();
        DocDto docDto = modelMapper.map(doc, DocDto.class);
        //When
        DocDto docDtoResult = mapper.convert(doc, DocDto.class);
        //Then
        assertEquals(docDto, docDtoResult);
    }

    private ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    private Doc createDocData(){
        Human human = new Human();
        human.setId(1);
        human.setName("Korney");
        human.setMarks(new int[]{1, 2, 3, 4, 5});
        List<String> stringList = new ArrayList<>();
        stringList.add("first");
        stringList.add("second");
        stringList.add("third");
        human.setDescriptions(stringList);

        Car car = new Car();
        car.setId(15);
        car.setName("Lada");
        car.setPrice(1200);
        car.setHuman(human);

        Car car2 = new Car();
        car2.setId(22);
        car2.setName("Volvo");
        car2.setPrice(1500);

        Car car3 = new Car();
        car3.setId(23);
        car3.setName("Audi");
        car3.setPrice(2000);
        List<Car> carList = new ArrayList<>();
        carList.add(car2);
        carList.add(car3);

        Car[] carsArray = new Car[3];
        carsArray[0] = car;
        carsArray[1] = car2;
        carsArray[2] = car3;

        Queue<Car> carQueue = new LinkedList<>();
        carQueue.add(car);
        carQueue.add(car2);
        carQueue.add(car3);

        Doc doc = new Doc();
        doc.setId(22);
        doc.setNumber("222-sss-555");
        doc.setExpiryDate(new Date());
        doc.setCar(car);
        doc.setCars(carList);
        doc.setCarsArray(carsArray);
//        doc.setCarQueue(carQueue);
        return doc;
    }
}
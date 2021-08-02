package practice.mapper;

import practice.document.Document;
import practice.document.DocumentDto;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args){

        Human human = new Human();
        human.setId(1);
        human.setName("Korney");
        human.setMarks(new int[] {1,2,3,4,5});
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

        Doc doc = new Doc();
        doc.setId(22);
        doc.setNumber("222-sss-555");
        doc.setExpiryDate(new Date());
        doc.setCar(car);
        doc.setCars(carList);

        Mapper mapper = new Mapper();
        DocDto docDto = mapper.run(doc, DocDto.class);
        System.out.println(doc);
        System.out.println(docDto);

        Mapper mapper1 = new Mapper();
        mapper1.addMetaDataSet(DocDto.class);
        String s = "";
    }
}

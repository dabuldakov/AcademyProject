package reflection;

import reflection.mapper.Mapper;
import reflection.model.Auto;
import reflection.model.House;
import reflection.model.Person;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Person person1 = new Person("Mark", 88004442222L);
        House house1 = new House(1, "Moscow city");
        Auto auto = new Auto("Blue", "676");
        house1.setDescription("Centre of city");
        house1.setReappearing(true);
        house1.setSelling(false);
        person1.setHouse(house1);
        person1.setAuto(auto);

        Mapper mapper = new Mapper();
        mapper.addMetaData(Person.class);
        String serialize = mapper.serialize(person1, Person.class);
        System.out.println("-----Serialize------");
        System.out.println(serialize);
        Person person2 = mapper.deSerialize("{\n" +
                "  \"name\": \"Arnold\",\n" +
                "  \"phoneNumber\": 555666777,\n" +
                "  \"house\": {\n" +
                "    \"id\": 5655,\n" +
                "    \"location\": \"Tomsk city\",\n" +
                "    \"selling\": true,\n" +
                "    \"reappearing\": false,\n" +
                "    \"description\": null\n" +
                "  },\n" +
                "  \"auto\": {\n" +
                "    \"id\": \"912\",\n" +
                "    \"color\": \"dark\"\n" +
                "  }\n" +
                "}", Person.class);
        System.out.println("-----Deserialize------");
        System.out.println(person2);

        House house = mapper.deSerialize("{\n" +
                "  \"id\": 5655,\n" +
                "  \"location\": \"Tomsk city\",\n" +
                "  \"selling\": true,\n" +
                "  \"reappearing\": false,\n" +
                "  \"description\": null\n" +
                "}", House.class);
        System.out.println("-----Deserialize------");
        System.out.println(house);

    }


}


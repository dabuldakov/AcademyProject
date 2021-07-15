package reflection;

import reflection.mapper.Mapper;
import reflection.model.House;
import reflection.model.Person;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Person person1 = new Person("Mark", 88004442222L);
        House house1 = new House(1, "Moscow city");
        house1.setDescription("Centre of city");
        house1.setReappearing(true);
        house1.setSelling(false);
        person1.setHouse(house1);

        Mapper personMapper = new Mapper();
        String serialize = personMapper.serialize(person1, Person.class);
        System.out.println("-----Serialize------");
        System.out.println(serialize);
        Person person2 = personMapper.deSerialize("{\n" +
                "  \"name\": \"Arnold\",\n" +
                "  \"telephone\": 555666777,\n" +
                "  \"house\": {\n" +
                "    \"id\": 5655,\n" +
                "    \"location\": \"Tomsk city\",\n" +
                "    \"selling\": true,\n" +
                "    \"reappearing\": false,\n" +
                "    \"description\": n\n" +
                "  }\n" +
                "}", Person.class);
        System.out.println("-----Deserialize------");
        System.out.println(person2);


        Mapper houseMapper = new Mapper();
        House house = houseMapper.deSerialize("{\n" +
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


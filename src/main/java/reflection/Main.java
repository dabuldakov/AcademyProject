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

        Mapper<Person> personMapper = new Mapper<>(Person.class);
        String serialize = personMapper.serialize(person1);
        System.out.println("-----Serialize------");
        System.out.println(serialize);
        Person person2 = (Person) personMapper.deSerialize("{\n" +
                "  \"name\": \"Arnold\",\n" +
                "  \"telephone\": 555666777,\n" +
                "  \"house\": {\n" +
                "    \"id\": 5655,\n" +
                "    \"location\": \"Tomsk city\",\n" +
                "    \"selling\": true,\n" +
                "    \"reappearing\": false,\n" +
                "    \"description\": n\n" +
                "  }\n" +
                "}");
        System.out.println("-----Deserialize------");
        System.out.println(person2);


        Mapper<House> houseMapper = new Mapper<>(House.class);
        House house = (House) houseMapper.deSerialize("{\n" +
                "  \"id\": 5655,\n" +
                "  \"location\": \"Tomsk city\",\n" +
                "  \"selling\": true,\n" +
                "  \"reappearing\": false,\n" +
                "  \"description\": null\n" +
                "}");
        System.out.println("-----Deserialize------");
        System.out.println(house);

    }


}


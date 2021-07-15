package reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Person person1 = new Person("Mark", 88004442222L);
        Mapper<Person> personMapper = new Mapper<>(Person.class);
        String serialize = personMapper.serialize(person1);
        System.out.println(serialize);
        Person person2 = (Person) personMapper.deSerialize("{\n" +
                "  \"name\": \"Arnold\",\n" +
                "  \"telephone\": 555666777,\n" +
                "  \"house\": {\n" +
                "    \"id\": 5655,\n" +
                "    \"location\": \"Tomsk city\",\n" +
                "    \"selling\": true,\n" +
                "    \"reappearing\": false,\n" +
                "    \"description\": null\n" +
                "  }\n" +
                "}");
        System.out.println(person2);


        Mapper<House> houseMapper = new Mapper<>(House.class);
        House house = (House) houseMapper.deSerialize("{\n" +
                "  \"id\": 5655,\n" +
                "  \"location\": \"Tomsk city\",\n" +
                "  \"selling\": true,\n" +
                "  \"reappearing\": false,\n" +
                "  \"description\": null\n" +
                "}");
        System.out.println(house);

    }


}


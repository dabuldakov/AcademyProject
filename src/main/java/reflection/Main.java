package reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Person person = new Person("Arnold", 555666777L);
        Mapper<Person> personMapper = new Mapper<>(Person.class);
        String serialize = personMapper.serialize(person);
        System.out.println(serialize);
        Person person1 = (Person) personMapper.deSerialize("{\"name\":\"Arnold\",\"phoneNumber\":555666777}");
        System.out.println(person1);


        Mapper<House> houseMapper = new Mapper<>(House.class);
        House house = (House) houseMapper.deSerialize("{\n" +
                "  \"id\": 5655,\n" +
                "  \"location\": \"Tomsk city\"\n" +
                "}");
        System.out.println(house);

    }


}


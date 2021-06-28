package homeWork;

import java.util.ArrayList;
import java.util.List;

public class Different {

    public static void main(String[] args) {

        Animals animals = new Animals(5, "First");
        List list = new ArrayList();
        list.add(15);
        list.add("test");
        list.add(animals);

        for (Object o : list) {
            if (o instanceof String) {
                System.out.println((String) o);
            } else if (o instanceof Integer) {
                System.out.println((int) o);
            } else if (o instanceof Animals) {
                System.out.println((Animals) o);
            }
        }
    }



static class Animals {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "Animals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Animals(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
}


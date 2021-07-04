package Restaurant;

import Restaurant.Exceptions.AddArrayException;

public interface Registration {

    Array getArray();

    void setArray(Array array);

    default void add(Object o) throws AddArrayException {
        Array array = getArray();
        for (int i = 0; i < array.size(); i++) {
            if (array.getArray()[i].equals(o)){
                throw new AddArrayException("This object exist in DB, please add new one. Object: " + o.toString());
            }
        }
        System.out.println("Object Registered.");
        array.add(o);
    }
}

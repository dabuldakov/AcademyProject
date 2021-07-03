package Restaurant;

import Restaurant.Exceptions.AddArrayException;

public interface Registration {

    Array getArray();

    default void add(Object o) throws AddArrayException {
        Array array = getArray();
        for (int i = 0; i < array.size(); i++) {
            if (array.getArray()[i].equals(o)){
                throw new AddArrayException("This object exist in DB, please add new one.");
            }
        }
        System.out.println("Object added in Registration");
        array.add(o);
    }
}

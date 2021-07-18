package restaurant;

import org.springframework.stereotype.Component;
import restaurant.exceptions.AddArrayException;

public interface Registration {

    ArrayList getArray();

    void setArray(ArrayList arrayList);

    default void add(Object o) throws AddArrayException {
        Class<?> oClass = o.getClass();
        ArrayList arrayList = getArray();

        if (arrayList.contain(o))
            throw new AddArrayException("This object exist in db, please addMaterial new one. Object: " + o.toString());

        System.out.println(oClass.getSimpleName() + " registered.");
        arrayList.add(o);
    }
}

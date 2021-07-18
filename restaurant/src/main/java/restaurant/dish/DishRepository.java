package restaurant.dish;

import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.Registration;
import restaurant.exceptions.NotFoundArrayException;

@Component
public class DishRepository implements Registration {

    private ArrayList dishArrayList;

    public DishRepository(DishDB dishDB) {
        dishArrayList = dishDB.getArrayList();
    }

    public Dish getByName(String name) throws NotFoundArrayException {

        for (int i = 0; i < dishArrayList.length(); i++) {
            Dish dish = (Dish) dishArrayList.get(i);
            if(dish.getName().equals(name))
                return dish;
        }
        throw new NotFoundArrayException("Name not found in DishDB.");
    }

    @Override
    public ArrayList getArray() {
        return dishArrayList;
    }

    @Override
    public void setArray(ArrayList arrayList) {
        dishArrayList = arrayList;
    }
}

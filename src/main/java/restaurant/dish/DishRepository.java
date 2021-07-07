package restaurant.dish;

import restaurant.Array;
import restaurant.exceptions.NotFoundArrayException;
import restaurant.Registration;

public class DishRepository implements Registration {

    private Array dishArray;

    public DishRepository(DishDB dishDB) {
        dishArray = dishDB.getArray();
    }

    public Dish getByName(String name) throws NotFoundArrayException {
        Object[] dishes = dishArray.getArray();
        for (Object o : dishes) {
            Dish dish = (Dish) o;
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        throw new NotFoundArrayException("Name not found in DishDB.");
    }

    @Override
    public Array getArray() {
        return dishArray;
    }

    @Override
    public void setArray(Array array) {
        dishArray = array;
    }
}

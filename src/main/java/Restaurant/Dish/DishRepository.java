package Restaurant.Dish;

import Restaurant.Array;
import Restaurant.Exceptions.NotFoundArrayException;

public class DishRepository {

    private Array dishArray;

    public DishRepository(Array array) {
        dishArray = array;
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
}

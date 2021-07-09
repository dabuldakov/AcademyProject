package restaurant.dish;

import restaurant.ArrayList;
import restaurant.exceptions.NotFoundArrayException;
import restaurant.Registration;

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

/*        Object[] dishes = dishArrayList.getArrayList();
        for (Object o : dishes) {
            Dish dish = (Dish) o;
            if (dish.getName().equals(name)) {
                return dish;
            }
        }*/
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

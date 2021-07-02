package Restaurant.Dish;

import Restaurant.Array;
import Restaurant.Registration;

public class DBDish implements Registration {

    Array array;

    public final Dish borsh = new Dish("Borsh", 150);
    public final Dish losos = new Dish("Losos", 270);
    public final Dish cake = new Dish("Cake", 70);
    public final Dish juice = new Dish("Juice", 50);
    public final Dish bread = new Dish("Bread", 5);

    public Array createDB(){

        Array dishArray = new DishArray();
        dishArray.add(borsh);
        dishArray.add(losos);
        dishArray.add(cake);
        dishArray.add(juice);
        dishArray.add(bread);
        array = dishArray;
        return dishArray;
    }

    @Override
    public Array getArray() {
        return array;
    }
}

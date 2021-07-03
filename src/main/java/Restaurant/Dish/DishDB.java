package Restaurant.Dish;

import Restaurant.Array;
import Restaurant.Registration;

public class DishDB implements Registration {

    private Array array;

    public DishDB() {
        createDB();
    }

    public Array createDB(){
        Dish borsh = new Dish("Borsh", 150);
        Dish losos = new Dish("Losos", 270);
        Dish cake = new Dish("Cake", 70);
        Dish juice = new Dish("Juice", 50);
        Dish bread = new Dish("Bread", 5);
        Array dishArray = new Array();
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

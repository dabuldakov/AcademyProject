package restaurant.dish;

import restaurant.ArrayList;

public class DishDB{

    private ArrayList arrayList;

    public DishDB() {
        createDB();
    }

    public ArrayList createDB(){
        Dish borsh = new Dish("Borsh", 150);
        Dish losos = new Dish("Losos", 270);
        Dish cake = new Dish("Cake", 70);
        Dish juice = new Dish("Juice", 50);
        Dish bread = new Dish("Bread", 5);
        ArrayList dishArrayList = new ArrayList();
        dishArrayList.add(borsh);
        dishArrayList.add(losos);
        dishArrayList.add(cake);
        dishArrayList.add(juice);
        dishArrayList.add(bread);
        arrayList = dishArrayList;
        return dishArrayList;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }
}

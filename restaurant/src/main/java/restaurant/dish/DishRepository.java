package restaurant.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.DataBase;
import restaurant.Registration;
import restaurant.exceptions.NotFoundArrayException;

import javax.annotation.PostConstruct;

@Component
public class DishRepository implements Registration {

    private ArrayList dishArrayList;

    @Autowired
    public DishRepository(DataBase dataBase) {
        dishArrayList = dataBase.getMap().get("dishDataBase");
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

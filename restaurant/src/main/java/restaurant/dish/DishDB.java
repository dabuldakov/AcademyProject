package restaurant.dish;

import org.springframework.stereotype.Component;
import restaurant.ArrayList;

@Component
public class DishDB{

    private ArrayList arrayList;

    public DishDB() {
        arrayList = new ArrayList();
    }

    public ArrayList getArrayList() {
        return arrayList;
    }
}

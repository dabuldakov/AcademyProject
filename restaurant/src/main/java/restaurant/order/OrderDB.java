package restaurant.order;

import org.springframework.stereotype.Component;
import restaurant.ArrayList;

public class OrderDB {

    private ArrayList arrayList;
    private int id;

    public OrderDB() {
        arrayList = new ArrayList();
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

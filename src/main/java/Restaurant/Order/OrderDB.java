package Restaurant.Order;

import Restaurant.Array;

public class OrderDB {

    private Array array;
    private int id;

    public OrderDB() {
        array = new Array();
    }

    public Array getArray() {
        return array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

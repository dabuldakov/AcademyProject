package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;
import Restaurant.Registration;

public class OrderDB implements Registration {

    private Array array;
    private int id;

    public OrderDB() {
        array = new Array();
    }

    public Order createOrder(Array dishArray, Customer customer){
        id = id + 1;
        Order order = new Order(id, dishArray, OrderStatus.CREATED, customer);
        array.add(order);
        System.out.println("-----------------------");
        System.out.println("Order [" + id + "] added in OrderDB");
        return order;
    }

    @Override
    public Array getArray() {
        return array;
    }

}

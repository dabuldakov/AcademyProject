package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;
import Restaurant.Dish.DishArray;
import Restaurant.Registration;

public class DBOrder implements Registration {

    private OrderArray array;
    private int id;

    public DBOrder() {
        array = new OrderArray();
    }

    public Order createOrder(DishArray dishArray, Customer customer){
        id = id + 1;
        Order order = new Order(id, dishArray, Status.CREATED, customer);
        array.add(order);
        System.out.println("-----------------------");
        System.out.println("Order [" + id + "] added in Registration");
        return order;
    }

    @Override
    public OrderArray getArray() {
        return array;
    }

}

package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;
import Restaurant.Dish.DishArray;
import Restaurant.Registration;

public class RegistrationOrder implements Registration {

    private Array array;
    private int id;

    public RegistrationOrder() {
        array = new Array();
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
    public Array getArray() {
        return array;
    }

}

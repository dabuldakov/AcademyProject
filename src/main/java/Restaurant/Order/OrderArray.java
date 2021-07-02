package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;

public class OrderArray extends Array {

    public OrderArray() {
        super();
    }

    public OrderArray(Object object) {
        super(object);
    }

    public OrderArray getOrdersByCustomer(Customer customer, OrderArray orderArray) {
        OrderArray array = new OrderArray();
        Object[] orders = orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            Order order = (Order) orders[i];
            if (order.getCustomer().equals(customer))
                array.add(orders[i]);
        }
        return array;
    }

    public OrderArray getOrdersByStatus(Status status, OrderArray orderArray) {
        OrderArray array = new OrderArray();
        Object[] orders = orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            Order order = (Order) orders[i];
            if (order.getStatus().equals(status))
                array.add(orders[i]);
        }
        return array;
    }
}

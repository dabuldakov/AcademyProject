package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;

public class OrderRepository {

    private Array orderArray;

    public OrderRepository(Array array) {
        orderArray = array;
    }

    public Array getOrdersByCustomer(Customer customer) {
        Array array = new Array();
        Object[] orders = orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            Order order = (Order) orders[i];
            if (order.getCustomer().equals(customer))
                array.add(orders[i]);
        }
        return array;
    }

    public Array getOrdersByStatus(OrderStatus orderStatus) {
        Array array = new Array();
        Object[] orders = orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            Order order = (Order) orders[i];
            if (order.getOrderStatus().equals(orderStatus))
                array.add(orders[i]);
        }
        return array;
    }
}

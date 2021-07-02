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
        OrderArray array = new OrderArray(new Customer());
        Order[] orders = (Order[]) orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            if (orders[0].getCustomer().equals(customer))
                array.add(orders[i]);
        }
        return array;
    }

    public OrderArray getOrdersByStatus(Status status, OrderArray orderArray) {
        OrderArray array = new OrderArray(new Customer());
        Order[] orders = (Order[]) orderArray.getArray();
        for (int i = 0; i < orderArray.size(); i++) {
            if (orders[0].getStatus().equals(status))
                array.add(orders[i]);
        }
        return array;
    }
}

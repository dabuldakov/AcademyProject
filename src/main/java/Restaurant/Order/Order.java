package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;

import java.util.Objects;

public class Order {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private int id;
    private Array dishList;
    private OrderStatus orderStatus;
    private Customer customer;

    Order(int id, Array dishList, OrderStatus orderStatus, Customer customer) {
        this.id = id;
        this.dishList = dishList;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dishList=" + dishList +
                ", orderStatus=" + ANSI_RED + orderStatus + ANSI_RESET +
                ", customer=" + customer +
                '}';
    }
}

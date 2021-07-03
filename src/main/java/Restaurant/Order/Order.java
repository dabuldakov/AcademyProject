package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;

import java.util.Objects;

public class Order {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    int id;
    Array dishList;
    OrderStatus orderStatus;
    Customer customer;

    public Order(int id, Array dishList, OrderStatus orderStatus, Customer customer) {
        this.id = id;
        this.dishList = dishList;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }

    public Order() {
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

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Array getDishList() {
        return dishList;
    }

    public void setDishList(Array dishList) {
        this.dishList = dishList;
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

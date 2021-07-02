package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;
import Restaurant.Dish.Dish;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    int id;
    Array dishList;
    Status status;
    Customer customer;

    public Order(int id, Array dishList, Status status, Customer customer) {
        this.id = id;
        this.dishList = dishList;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }
}

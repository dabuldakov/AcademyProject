package restaurant.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.Constants;
import restaurant.customer.Customer;

import java.util.Objects;

@Component
@Scope("prototype")
public class Order {

    private int id;
    private ArrayList dishList;
    private OrderStatus orderStatus;
    private Customer customer;

    @Autowired
    public Order(OrderRepository orderRepository) {
        this.id = orderRepository.getId();
    }

    public ArrayList getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList dishList) {
        this.dishList = dishList;
    }

    public void setCustomer(Customer customer) {
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
        return "order{" +
                "id=" + id +
                ", dishList=" + dishList +
                ", orderStatus=" + Constants.ANSI_RED + orderStatus + Constants.ANSI_RESET +
                ", customer=" + customer +
                '}';
    }
}

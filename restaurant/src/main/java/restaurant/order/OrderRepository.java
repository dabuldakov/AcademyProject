package restaurant.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.Registration;
import restaurant.customer.Customer;

@Component
public class OrderRepository implements Registration {

    private ArrayList orderArrayList;
    private int id;
    private OrderDB orderDB;

    public OrderRepository(OrderDB orderDB) {
        this.orderArrayList = orderDB.getArrayList();
        this.id = orderDB.getId();
        this.orderDB = orderDB;
    }

    public Order createOrder(ArrayList dishArrayList, Customer customer){
        id++;
        orderDB.setId(id);
        Order order = new Order(this);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setCustomer(customer);
        order.setDishList(dishArrayList);
        orderArrayList.add(order);
        System.out.println("-----------------------");
        System.out.println("order [" + id + "] added in OrderDB");
        return order;
    }

    public ArrayList getOrdersByCustomer(Customer customer) {
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < orderArrayList.length(); i++) {
            Order order = (Order) orderArrayList.get(i);
            if(order.getCustomer().equals(customer))
                arrayList.add(order);
        }
        return arrayList;
    }

    public ArrayList getOrdersByStatus(OrderStatus orderStatus) {
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < orderArrayList.length(); i++) {
            Order order = (Order) orderArrayList.get(i);
            if(order.getOrderStatus().equals(orderStatus))
                arrayList.add(order);
        }
        return arrayList;
    }

    @Override
    public ArrayList getArray() {
        return orderArrayList;
    }

    @Override
    public void setArray(ArrayList arrayList) {
        orderArrayList = arrayList;
    }

    public ArrayList getOrderArrayList() {
        return orderArrayList;
    }

    public void setOrderArrayList(ArrayList orderArrayList) {
        this.orderArrayList = orderArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderDB getOrderDB() {
        return orderDB;
    }

    public void setOrderDB(OrderDB orderDB) {
        this.orderDB = orderDB;
    }
}

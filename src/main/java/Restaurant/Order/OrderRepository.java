package Restaurant.Order;

import Restaurant.Array;
import Restaurant.Customer.Customer;
import Restaurant.Registration;

public class OrderRepository implements Registration {

    private Array orderArray;
    private int id;
    private OrderDB orderDB;

    public OrderRepository(OrderDB orderDB) {
        this.orderArray = orderDB.getArray();
        this.id = orderDB.getId();
        this.orderDB = orderDB;
    }

    public Order createOrder(Array dishArray, Customer customer){
        id = id + 1;
        orderDB.setId(id);
        Order order = new Order(id, dishArray, OrderStatus.CREATED, customer);
        orderArray.add(order);
        System.out.println("-----------------------");
        System.out.println("Order [" + id + "] added in OrderDB");
        return order;
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

    @Override
    public Array getArray() {
        return orderArray;
    }

    @Override
    public void setArray(Array array) {
        orderArray = array;
    }

    public Array getOrderArray() {
        return orderArray;
    }

    public void setOrderArray(Array orderArray) {
        this.orderArray = orderArray;
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

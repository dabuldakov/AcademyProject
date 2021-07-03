package Restaurant;

import Restaurant.Order.Order;
import Restaurant.Order.OrderDB;
import Restaurant.Order.OrderStatus;

public interface Service {

    OrderStatus getStatus();

    default void run(Order order, OrderDB orderDB){
        for (int i = 0; i < orderDB.getArray().size(); i++) {
            Order findOrder = (Order) orderDB.getArray().getArray()[i];
            if (findOrder.equals(order)){
                findOrder.setOrderStatus(getStatus());
                System.out.println("-----------------------");
                System.out.println(getStatus() + " order id: " + order.getId());
            }
        }
    }

}

package Restaurant;

import Restaurant.Order.Order;
import Restaurant.Order.DBOrder;
import Restaurant.Order.Status;

public interface Service {

    Status getStatus();

    default void run(Order order, DBOrder dbOrder){
        for (int i = 0; i < dbOrder.getArray().size(); i++) {
            Order findOrder = (Order) dbOrder.getArray().getArray()[i];
            if (findOrder.equals(order)){
                findOrder.setStatus(getStatus());
                System.out.println("-----------------------");
                System.out.println(getStatus() + " order");
            }
        }
    }

}

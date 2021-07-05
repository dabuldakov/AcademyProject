package Restaurant;

import Restaurant.Order.Order;
import Restaurant.Order.OrderDB;
import Restaurant.Order.OrderRepository;
import Restaurant.Order.OrderStatus;
import org.apache.xpath.operations.Or;

public interface Service {

    OrderStatus getStatusStart();

    OrderStatus getStatusFinished();

    default void run(Order order, OrderRepository orderRepository){
        for (int i = 0; i < orderRepository.getArray().size(); i++) {
            Order findOrder = (Order) orderRepository.getArray().getArray()[i];
            if(findOrder.equals(order)){
                findOrder.setOrderStatus(getStatusStart());
                System.out.println("-----------------------");
                System.out.println(getStatusStart() + " order id: " + order.getId());
            }
        }
    }

    default void finished(Order order, OrderRepository orderRepository){
        for (int i = 0; i < orderRepository.getArray().size(); i++) {
            Order findOrder = (Order) orderRepository.getArray().getArray()[i];
            if(findOrder.equals(order)){
                findOrder.setOrderStatus(getStatusFinished());
                System.out.println("-----------------------");
                System.out.println(getStatusFinished() + " order id: " + order.getId());
            }
        }
    }



}

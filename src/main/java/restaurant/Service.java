package restaurant;

import restaurant.order.Order;
import restaurant.order.OrderRepository;
import restaurant.order.OrderStatus;

public interface Service {

    OrderStatus getStatusStart();
    OrderStatus getStatusFinished();
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";

    default void run(Order order, OrderRepository orderRepository){
        for (int i = 0; i < orderRepository.getArray().size(); i++) {
            Order findOrder = (Order) orderRepository.getArray().getArray()[i];
            if(findOrder.equals(order)){
                findOrder.setOrderStatus(getStatusStart());
                System.out.println("-----------------------");
                System.out.println(ANSI_RED + getStatusStart() + ANSI_RESET + " order id: " + order.getId());
            }
        }
    }

    default void finished(Order order, OrderRepository orderRepository){
        for (int i = 0; i < orderRepository.getArray().size(); i++) {
            Order findOrder = (Order) orderRepository.getArray().getArray()[i];
            if(findOrder.equals(order)){
                findOrder.setOrderStatus(getStatusFinished());
                System.out.println("-----------------------");
                System.out.println(ANSI_RED + getStatusFinished() + ANSI_RESET + " order id: " + order.getId());
            }
        }
    }



}

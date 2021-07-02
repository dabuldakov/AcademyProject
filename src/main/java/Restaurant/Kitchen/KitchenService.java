package Restaurant.Kitchen;

import Restaurant.Order.Order;
import Restaurant.Order.RegistrationOrder;
import Restaurant.Order.Status;

public class KitchenService {

    public static void cocking(Order order, RegistrationOrder registrationOrder){

        for (int i = 0; i < registrationOrder.getArray().size(); i++) {
            Order findOrder = (Order) registrationOrder.getArray().getArray()[i];
            if (findOrder.equals(order)){
                findOrder.setStatus(Status.COOKING);
                System.out.println("-----------------------");
                System.out.println("Coocking order");
            }
        }
    }
}

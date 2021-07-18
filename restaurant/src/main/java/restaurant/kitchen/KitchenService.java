package restaurant.kitchen;

import restaurant.Service;
import restaurant.order.OrderStatus;

public class KitchenService implements Service {

    @Override
    public OrderStatus getStatusStart() {
        return OrderStatus.COOKING;
    }

    @Override
    public OrderStatus getStatusFinished() {
        return OrderStatus.COOKED;
    }
}

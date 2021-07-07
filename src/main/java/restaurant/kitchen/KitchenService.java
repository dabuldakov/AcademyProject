package restaurant.kitchen;

import restaurant.order.OrderStatus;
import restaurant.Service;

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

package restaurant2.kitchen;

import restaurant2.order.OrderStatus;
import restaurant2.Service;

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

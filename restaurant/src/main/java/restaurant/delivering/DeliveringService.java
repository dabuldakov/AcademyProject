package restaurant.delivering;

import restaurant.Service;
import restaurant.order.OrderStatus;

public class DeliveringService implements Service {
    @Override
    public OrderStatus getStatusStart() {
        return OrderStatus.DELIVERING;
    }

    @Override
    public OrderStatus getStatusFinished() {
        return OrderStatus.DELIVERED;
    }
}

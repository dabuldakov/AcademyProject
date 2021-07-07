package restaurant.delivering;

import restaurant.order.OrderStatus;
import restaurant.Service;

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

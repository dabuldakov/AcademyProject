package restaurant2.delivering;

import restaurant2.order.OrderStatus;
import restaurant2.Service;

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

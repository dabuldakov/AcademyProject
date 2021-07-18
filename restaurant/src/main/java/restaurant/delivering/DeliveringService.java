package restaurant.delivering;

import org.springframework.stereotype.Component;
import restaurant.Service;
import restaurant.order.OrderStatus;

@Component
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

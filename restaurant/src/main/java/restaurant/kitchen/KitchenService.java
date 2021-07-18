package restaurant.kitchen;

import org.springframework.stereotype.Component;
import restaurant.Service;
import restaurant.order.OrderStatus;

@Component
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

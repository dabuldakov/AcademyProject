package Restaurant.Kitchen;

import Restaurant.Order.OrderStatus;
import Restaurant.Service;

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

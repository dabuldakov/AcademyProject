package Restaurant.Kitchen;

import Restaurant.Order.OrderStatus;
import Restaurant.Service;

public class KitchenService implements Service {

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.COOKING;
    }
}

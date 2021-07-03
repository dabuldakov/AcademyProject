package Restaurant.Delivering;

import Restaurant.Order.OrderStatus;
import Restaurant.Service;

public class DeliveringService implements Service {
    @Override
    public OrderStatus getStatus() {
        return OrderStatus.DELIVERING;
    }
}

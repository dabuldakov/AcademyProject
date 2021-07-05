package Restaurant.Delivering;

import Restaurant.Order.OrderStatus;
import Restaurant.Service;

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

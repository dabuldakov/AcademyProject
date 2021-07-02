package Restaurant.Kitchen;

import Restaurant.Order.Order;
import Restaurant.Order.DBOrder;
import Restaurant.Order.Status;
import Restaurant.Service;

public class KitchenService implements Service {

    @Override
    public Status getStatus() {
        return Status.COOKING;
    }
}

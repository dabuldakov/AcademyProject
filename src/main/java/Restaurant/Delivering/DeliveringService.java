package Restaurant.Delivering;

import Restaurant.Order.Status;
import Restaurant.Service;

public class DeliveringService implements Service {
    @Override
    public Status getStatus() {
        return Status.DELIVERING;
    }
}
